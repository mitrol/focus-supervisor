package net.mitrol.supervisor.service;

import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaConsumerService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(KafkaConsumerService.class);

    @Autowired
    private KafkaReceiver receiver, receiver2;

    @PostConstruct
    public void init() {
        this.receiver.registerListener("supervisor2", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.info("processMessage " + source + " " + topic + " " + value);
            }
        });
        this.receiver2.registerListener("dashboardUpdated2", new KafkaReceiverListener<String>() {
            @Override
            public void processMessage(String source, String topic, String value) {
                logger.info("processMessage " + source + " " + topic + " " + value);
            }
        });
    }
}
