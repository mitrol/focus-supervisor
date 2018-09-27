package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.enums.EventType;
import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.connector.util.SupervisorEventJob;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * @author ladassus
 */
@Service
public class SupervisorEventService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventService.class);

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SupervisorKafkaService kafkaService;

    public void eventMessageProcess (String message) {
        EventRequest event = getEventMessageValidated(message);
        EventType eventType  = EventType.valueOf(event.getEventType().toUpperCase());
        switch (eventType) {
            case SUBSCRIBE: {
                subscribeEvent(event);
                break;
            }
            case UNSUBSCRIBE: {
                unsubscribeEvent(event);
                break;
            }
            case FILTERCHANGE: {
                filterChangedEvent(event);
                break;
            }
            default: {
                break;
            }
        }
    }

    private synchronized void subscribeEvent (EventRequest event){
        //
        JobDetail job = JobBuilder
                .newJob(SupervisorEventJob.class)
                .withIdentity("job" + event.getId())
                .build();
        job.getJobDataMap().put("event", event);
        // TODO: job.getJobDataMap().put("esService", esService);
        job.getJobDataMap().put("kafkaService", kafkaService);
        //
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger" + event.getId(), event.getId())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/" + event.getRefreshInterval() + " * * * * ?"))
                .endAt(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .build();
        //
        try {
            scheduler.scheduleJob(job, trigger);
            logger.debug("Scheduled job for message event: " + event.toString());
        } catch (SchedulerException e) {
            logger.error(e, "Error scheduling job for message event: " + event.toString());
        }
    }

    private synchronized void unsubscribeEvent (EventRequest event){
        TriggerKey tkey = new TriggerKey("trigger" + event.getId(), event.getId());
        try {
            scheduler.unscheduleJob(tkey);
            logger.debug("Unscheduled job for message event: " + event.toString());
        } catch (SchedulerException e) {
            logger.error(e, "Error unscheduling job for message event: " + event.toString());
        }
    }

    private void filterChangedEvent (EventRequest event){
        unsubscribeEvent(event);
        subscribeEvent(event);
    }

    private EventRequest getEventMessageValidated (String message){
        Validate.notNull(message, "Event message string cannot be null");
        EventRequest event = null;
        try {
            event = JsonMapper.getInstance().getObjectFromString(message, EventRequest.class);
        } catch (JSONException e) {
            logger.error(e);
        }
        Validate.notNull(event, "Event message json mapper cannot be null");
        Validate.notNull(event.getId(), "Event message id cannot be null");
        Validate.notEmpty(event.getEventType(), "Event message type cannot be empty");
        //
        EventType eventType  = EventType.valueOf(event.getEventType().toUpperCase());
        switch (eventType) {
            case SUBSCRIBE: {
                Validate.notNull(event.getRefreshInterval(), "Event message refreshInterval cannot be null");
                Validate.notNull(event.getAgentId(), "Event message agentId cannot be null");
                Validate.notEmpty(event.getWidgetType(), "Event message widget type cannot be empty");
                break;
            }
            case UNSUBSCRIBE:{
                break;
            }
            case FILTERCHANGE:{
                break;
            }
            default:{
                break;
            }
        }
        return event;
    }
}
