package net.mitrol.focus.supervisor.mitacd.connector.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ladassus
 */
@Service
public class WebhookService {

    @Autowired
    private MitAcdKafkaService kafkaService;

    public void buildWebhookData (String data) {
        Validate.notNull(data, "data must be not null");
        this.kafkaService.sender(data);
    }
}
