package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author ladassus
 */
@Service
public class SupervisorKafkaService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorKafkaService.class);

    @Autowired
    private KafkaReceiver receiver;

    @Autowired
    private MitAcdMessageService msgService;

    @PostConstruct
    public void init() {

        this.receiver.registerListener("supervisor", new KafkaReceiverListener<String>() {

            @Override
            public void processMessage(String source, String topic, String value) {
                logger.debug("processMessage " + source + " " + topic + " " + value);
                try {
                    msgService.kafkaMsgProcess(value);
                } catch (JSONException e) {
                    logger.error(e, "processMessage " + source + " " + topic + " " + value);
                }
            }
        });
    }
}
