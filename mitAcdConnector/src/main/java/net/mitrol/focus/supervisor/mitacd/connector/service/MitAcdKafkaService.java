package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MitAcdKafkaService {

    private final KafkaSender kafkaSender;
    @Value("${kafka.topics}")
    private String kafka_topic;

    @Autowired
    public MitAcdKafkaService(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public void sender(String message) {
        this.kafkaSender.send(kafka_topic, message);
    }
}
