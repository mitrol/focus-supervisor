package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;
import net.mitrol.kafka.KafkaSender;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author ladassus
 */
@Service
public class SupervisorKafkaService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorKafkaService.class);

    @Autowired
    private KafkaReceiver kafkaMitAcdReceiver, kafkaEventReceiver;
    @Autowired
    private KafkaSender kafkaEventSender;
    @Autowired
    private MitAcdMessageService mitAcdService;
    @Autowired
    private SupervisorEventService eventService;

    @PostConstruct
    public void init() {
        this.kafkaMitAcdReceiver.registerListener("supervisor", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.debug("Kafka mitAcd message to process: "
                        + source + " " + topic + " " + value);
                mitAcdService.mitAcdMessageProcess(value);
            }
        });
        this.kafkaEventReceiver.registerListener("supervision.event.request", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.info("Kafka supervisor event message request to process: "
                        + source + " " + topic + " " + value);
                eventService.eventMessageProcess(value);
            }
        });
    }

    public void sender(String topic, String message) {
        logger.debug("Supervisor event message response: " + message);
        this.kafkaEventSender.send(topic, message);
    }
}
