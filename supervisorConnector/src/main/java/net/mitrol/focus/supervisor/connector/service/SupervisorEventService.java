package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.event.EventMessage;
import net.mitrol.focus.supervisor.connector.util.EventMessageJob;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ladassus
 */
@Service
public class SupervisorEventService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventService.class);

    @Autowired
    private Scheduler scheduler;

    public void eventMessageProcess (String message) throws JSONException {
        Validate.notNull(message, "Supervisor event message cannot be null");
        EventMessage event = JsonMapper.getInstance().getObjectFromString(message, EventMessage.class);
        //TODO: do validate for all fiels on event object
        switch (event.getEventType()){
            case "subscribe" :{
                subscribeEvent(event);
                break;
            }
            case "unsubscribe" :{
                unsubscribeEvent(event);
                break;
            }
            case "filterChange" :{
                filterChangedEvent(event);
                break;
            }
            default:{
                break;
            }
        }
    }

    private synchronized void subscribeEvent (EventMessage event){
        //
        JobDetail job = JobBuilder
                .newJob(EventMessageJob.class)
                .withIdentity("job" + event.getId())
                .build();
        job.getJobDataMap().put("event", event);
        //
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger" + event.getAgentId(), event.getWidgetType())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/" + event.getRefreshInterval() + " * * * * ?"))
                .build();
        //
        try {
            scheduler.scheduleJob(job, trigger);
            logger.debug("Scheduled job for message event: " + event.toString());
        } catch (SchedulerException e) {
            logger.error(e, "Error scheduling job for message event: " + event.toString());
        }
    }

    private synchronized void unsubscribeEvent (EventMessage event){
        TriggerKey tkey = new TriggerKey("trigger" + event.getAgentId(), event.getWidgetType());
        try {
            scheduler.unscheduleJob(tkey);
            logger.debug("Unscheduled job for message event: " + event.toString());
        } catch (SchedulerException e) {
            logger.error(e, "Error unscheduling job for message event: " + event.toString());
        }
    }

    private void filterChangedEvent (EventMessage event){
        unsubscribeEvent(event);
        subscribeEvent(event);
    }
}
