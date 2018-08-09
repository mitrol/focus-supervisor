package net.mitrol.focus.supervisor.mitacd.connector.controller;

import net.mitrol.focus.supervisor.mitacd.connector.service.MitacdConnectorMessageService;
import net.mitrol.mitct.mitacd.event.AgentCampaignRelationEvent;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import net.mitrol.utils.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ladassus
 */
@RestController
@RequestMapping("/")
public class MitacdConnectorController {

    @Autowired
    private MitacdConnectorMessageService msgService;

    @RequestMapping(method = RequestMethod.POST, path = "/agents")
    public void sendAgentEvent(@RequestBody AgentEvent agentEvent) {
        msgService.processEvent(getStringFromObjectType(agentEvent, AgentEvent.TYPE));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/interactions")
    public void sendInteractionEvent(@RequestBody InteractionEvent interactionEvent) {
        msgService.processEvent(getStringFromObjectType(interactionEvent, InteractionEvent.TYPE));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/agent-campaing-relation")
    public void sendAgentCampaignRelationEvent(@RequestBody AgentCampaignRelationEvent agentCampaignRelationEvent) {
        msgService.processEvent(getStringFromObjectType(agentCampaignRelationEvent, AgentCampaignRelationEvent.TYPE));
    }

    private String getStringFromObjectType (Object obj, String type){
        return JsonMapper.getInstance().getStringJsonFromObject(
                new MitAcdEvent(type, JsonMapper.getInstance().getStringJsonFromObject(obj)));
    }
}

