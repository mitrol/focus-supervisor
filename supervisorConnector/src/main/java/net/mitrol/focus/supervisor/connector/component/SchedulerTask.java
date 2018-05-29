package net.mitrol.focus.supervisor.connector.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ladassus
 */
@Component
public class SchedulerTask {

    @Value("${scheduler.task.enabled:false}")
    private boolean scheduledJobEnabled;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=300)
    public void publishMessage(){
        if (!scheduledJobEnabled) {
            return;
        }
        //TODO
        template.convertAndSend("/topic/message", "messages here");
    }
}