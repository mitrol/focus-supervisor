package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.enums.EventType;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.focus.supervisor.common.event.EventFilter;
import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.connector.util.SupervisorEventJob;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author ladassus
 */
@Service
public class SupervisorEventService implements SupervisorEvent{

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventService.class);
    public static final String JOB_PREFIX="job";
    public static final String TRIGGER_PREFIX="trigger";

    @Value("${kafka.topic.supervision.event.request}")
    private String topic_supervision_request_name;
    @Value("${quartz.job.schedule.trigger.endAt:30}")
    private Long triggerEndTime;
    @Value("${quartz.job.schedule.trigger.interval:2}")
    private Long refreshInterval;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SupervisorKafkaService kafkaService;


    @Override
    public void processEvent(EventRequest event) {
        Validate.notNull(event, "Event Request cannot be null");
        EventType eventType  = EventType.valueOf(event.getEventType().toUpperCase());
        if (eventType != null) {
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
                    changeEventFilter(event, null);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Override
    public void subscribeEvent (EventRequest event){
        Validate.notNull(event, "Event Request cannot be null");
        unsubscribeEvent(event);
        JobDetail job = JobBuilder
                .newJob(SupervisorEventJob.class)
                .withIdentity(JOB_PREFIX + event.getId(), event.getWidgetType())
                .build();
        job.getJobDataMap().put("event", event);
        // TODO: job.getJobDataMap().put("esService", esService);
        job.getJobDataMap().put("kafkaService", kafkaService);
        job.getJobDataMap().put("kafkaTopicName", topic_supervision_request_name);
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(TRIGGER_PREFIX + event.getId(), event.getWidgetType())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/" + refreshInterval + " * * * * ?"))
                .endAt(Date.from(Instant.now().plus(triggerEndTime, ChronoUnit.MINUTES)))
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new MitrolSupervisorError("Quartz-Jobs scheduler error", e);
        }
    }

    @Override
    public void unsubscribeEvent (EventRequest event) {
        Validate.notNull(event, "Event Request cannot be null");
        JobKey jobKey= new JobKey(JOB_PREFIX + event.getId(), event.getWidgetType());
        TriggerKey triggerKey = new TriggerKey(TRIGGER_PREFIX + event.getId(), event.getWidgetType());
        try {
            if(scheduler.checkExists(jobKey)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw new MitrolSupervisorError("Quartz-Jobs scheduler error", e);
        }
    }

    @Override
    public void changeEventFilter(EventRequest event, EventFilter filter) {
        subscribeEvent(event);
    }

    @Override
    public void pauseEvent(EventRequest event) {
       //TODO
    }

    @Override
    public void playEvent(EventRequest event) {
       //TODO
    }
}
