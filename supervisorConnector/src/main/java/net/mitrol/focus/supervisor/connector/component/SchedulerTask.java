package net.mitrol.focus.supervisor.connector.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ladassus
 */
@Component
public class SchedulerTask {

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=300)
    public void publishMessage(String msg){
        template.convertAndSend("/topic/message", msg);
    }
}
