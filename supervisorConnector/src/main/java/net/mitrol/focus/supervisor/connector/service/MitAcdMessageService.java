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

@Service
public class MitAcdMessageService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdMessageService.class);

    @Value("${index.type}")
    private String index_type;
    @Value("${index.agent}")
    private String index_agent;
    @Value("${index.interaction}")
    private String index_interaction;
    @Value("${index.agent.interaction.relation}")
    private String index_agent_campaign_relation;

    @Autowired
    private ESHighLevelClientService esService;

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
        esService.buildDocumentIndex(agentEvent, ESUtil.getESIndexNameDateValue(index_agent), index_type, "");
    }

    private void processInteractionEvent(InteractionEvent interactionEvent) {
        esService.buildDocumentIndex(interactionEvent, ESUtil.getESIndexNameDateValue(index_interaction), index_type, "");
    }

    private void processAgentCampaignRelationEvent(AgentCampaignRelationEvent agentCampaignRelationEvent) {
        esService.buildDocumentIndex(agentCampaignRelationEvent, ESUtil.getESIndexNameDateValue(index_interaction), index_type, "");
    }

}
