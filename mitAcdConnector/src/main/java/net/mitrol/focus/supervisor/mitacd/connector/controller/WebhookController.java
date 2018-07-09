package net.mitrol.focus.supervisor.mitacd.connector.controller;

import net.mitrol.focus.supervisor.mitacd.connector.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ladassus
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    @RequestMapping(method = RequestMethod.POST)
    public void webhookMitACD(@RequestBody String data) {
        webhookService.buildWebhookData(data);
    }
}
