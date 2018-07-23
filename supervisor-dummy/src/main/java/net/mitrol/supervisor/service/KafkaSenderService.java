package net.mitrol.supervisor.service;


import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import net.mitrol.kafka.KafkaSender;
/**
 * 
 * @author ladassus
 *
 */
@Service
public class KafkaSenderService {

    private static Logger logger = Logger.getLogger("KafkaSenderService");

    private final KafkaSender kafkaSender;

    @Autowired
    public KafkaSenderService(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public void sender(String topic, String message) {
        this.kafkaSender.send(topic, message);
    }

}