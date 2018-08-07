package net.mitrol.focus.supervisor.mitacd.connector_generator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.mitrol.focus.supervisor.mitacd.connector_generator.service.WebhookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MitAcdConnectorMessageGeneratorController {

    private WebhookService webhookService;

    @RequestMapping(method = RequestMethod.POST, path = "/agents")
    public void sendAgentEvent() throws JsonProcessingException {
        webhookService.sendAgentEvent();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/interactions")
    public void sendInteractionEvent() throws JsonProcessingException {
        webhookService.sendInteractionEvent();
    }

}
