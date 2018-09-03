package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;
import net.mitrol.kafka.KafkaSender;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONException;
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
    private KafkaReceiver mitAcdReceiver, eventReceiver;
    @Autowired
    private KafkaSender eventSender;
    @Autowired
    private MitAcdMessageService mitAcdService;
    @Autowired
    private SupervisorEventService eventService;

    @PostConstruct
    public void init() {
        this.mitAcdReceiver.registerListener("supervisor", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.debug("Kafka mitAcd message to process: "
                        + source + " " + topic + " " + value);
                try {
                    mitAcdService.mitAcdMessageProcess(value);
                } catch (JSONException e) {
                    logger.error(e, "Kafka mitAcd message to process error: "
                            + source + " " + topic + " " + value);
                }
            }
        });
        this.eventReceiver.registerListener("supervisor.event.request", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.debug("Kafka supervisor event message request to process: "
                        + source + " " + topic + " " + value);
                try {
                    eventService.eventMessageProcess(value);
                } catch (JSONException e) {
                    logger.error(e, "Kafka Supervisor event message request to process error: "
                            + source + " " + topic + " " + value);
                }
            }
        });
    }

    public void sender(String topic, String message) {
        logger.debug("Supervisor event message response: " + message);
        this.eventSender.send(topic, message);
    }
}
