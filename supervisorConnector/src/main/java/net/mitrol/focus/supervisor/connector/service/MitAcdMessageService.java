package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.util.ESUtil;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.mitct.mitacd.event.AgentCampaignRelationEvent;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MitAcdMessageService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdMessageService.class);

    @Value("${index.type}")
    private String index_type;
    @Value("${index.agent}")
    private String index_agent;
    @Value("${index.interaction}")
    private String index_interaction;
    @Value("${index_agent_campaign_relation}")
    private String index_agent_campaign_relation;
    @Value("${bulk_size}")
    private Integer bulk_size;

    @Autowired
    private ESHighLevelClientService esService;

    List events = new ArrayList<Object>();

    protected final void kafkaMsgProcess(String message) throws JSONException {

        MitAcdEvent mitAcdEvent = JsonMapper.getInstance().getObjectFromString(message, MitAcdEvent.class);
        String type = mitAcdEvent.getType();
        String payload = mitAcdEvent.getPayload();

        switch (type) {
            case AgentEvent.TYPE:
                processAgentEvent(JsonMapper.getInstance().getObjectFromString(payload, AgentEvent.class));
                break;
            case InteractionEvent.TYPE:
                processInteractionEvent(JsonMapper.getInstance().getObjectFromString(payload, InteractionEvent.class));
                break;
            case AgentCampaignRelationEvent.TYPE:
                processAgentCampaignRelationEvent(JsonMapper.getInstance().getObjectFromString(payload, AgentCampaignRelationEvent.class));
                break;
        }
    }

    private void processAgentEvent(AgentEvent agentEvent) {
        if (events.size() < bulk_size){
            events.add(agentEvent);
        } else {
            esService.buildDocumentIndex(ESUtil.getESIndexNameDateValue(index_agent), index_type, events);
            events.clear();
        }
    }

    private void processInteractionEvent(InteractionEvent interactionEvent) {
        if (events.size() < bulk_size){
            events.add(interactionEvent);
        } else {
            esService.buildDocumentIndex(ESUtil.getESIndexNameDateValue(index_interaction), index_type, events);
            events.clear();
        }
    }

    private void processAgentCampaignRelationEvent(AgentCampaignRelationEvent agentCampaignRelationEvent) {
        if (events.size() < bulk_size){
            events.add(agentCampaignRelationEvent);
        } else {
            esService.buildDocumentIndex(ESUtil.getESIndexNameDateValue(index_agent_campaign_relation), index_type, events);
            events.clear();
        }
    }
}
