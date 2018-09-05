package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.util.ESUtil;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.mitct.mitacd.event.AgentCampaignRelationEvent;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.AgentState;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MitAcdMessageService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdMessageService.class);

    @Value("${index.type:_doc}")
    private String index_type;
    @Value("${index.agent:agent}")
    private String index_agent;
    @Value("${index.interaction:interaction}")
    private String index_interaction;
    @Value("${index_agent_campaign_relation:agentCampaignRelation}")
    private String index_agent_campaign_relation;
    @Value("${bulk_size:10000}")
    private Integer bulk_size;

    @Autowired
    private ESHighLevelClientService esService;

    List events = new ArrayList<Object>();

    protected final void mitAcdMessageProcess(String message) throws JSONException {
        Validate.notNull(message, "MitAcd message cannot be null");
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

    /**
     * Only for dummy and create bulk
     * */
    /*private void processAgentEvent(AgentEvent agentEvent) {
        Date date = new Date();
        for (int i = 0; i < 1100000 ; i++) {
            int id = ThreadLocalRandom.current().nextInt(1,  50);
            AgentEvent agentEvent1 = new AgentEvent();
            agentEvent1.setTimestamp(date);
            agentEvent1.setUserId(id);
            agentEvent1.setGroupId(agentEvent.getGroupId());
            int stateID = ThreadLocalRandom.current().nextInt(1,  9);
            agentEvent1.setState(AgentState.getFromCode(stateID));
            if (events.size() < bulk_size){
                events.add(agentEvent1);
            } else {
                esService.buildDocumentIndex(ESUtil.getESIndexNameDateValue(index_agent), index_type, events);
                events.clear();
            }
        }
    }*/
}
