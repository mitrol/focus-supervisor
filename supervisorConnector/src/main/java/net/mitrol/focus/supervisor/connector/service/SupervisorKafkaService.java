package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;
import net.mitrol.kafka.KafkaSender;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author ladassus
 */
@Service
public class SupervisorKafkaService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorKafkaService.class);

    @Value("${kafka.topic.supervision.event.response}")
    private String topic_supervision_response_name;
    @Value("${kafka.topic.supervision.mitacd}")
    private String topic_supervision_mitacd_name;
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
        this.kafkaMitAcdReceiver.registerListener(topic_supervision_mitacd_name, new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.debug("Kafka mitAcd message to process: "
                        + source + " " + topic + " " + value);
                mitAcdService.mitAcdMessageProcess(value);
            }
        });
        this.kafkaEventReceiver.registerListener(topic_supervision_response_name, new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String message) {
                logger.info("Receiving message from kafka topic -> " + topic + " message -> " + message);
                Validate.notNull(message, "Kafka value to consume cannot be null");
                try {
                    EventRequest event = JsonMapper.getInstance().getObjectFromString(message, EventRequest.class);
                    eventService.processEvent(event);
                } catch (JSONException e) {
                    logger.error(e);
                }
            }
        });
    }

    public void sender(String topic, String message) {
        logger.info("Sending message to kafka topic -> " + topic + " message -> " + message);
        this.kafkaEventSender.send(topic, message);
    }
}
