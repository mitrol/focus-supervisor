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
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void mitAcdMessageProcess(String message) {
        MitAcdEvent mitAcdEvent = getMitAcdMessageValidated(message);
        String type = mitAcdEvent.getType();
        String payload = mitAcdEvent.getPayload();
        try {
            switch (type) {
                case AgentEvent.TYPE:
                    processAgentEvent(JsonMapper.getInstance().getObjectFromString(payload, AgentEvent.class));
                    break;
                case InteractionEvent.TYPE:
                    processInteractionEvent(JsonMapper.getInstance()
                            .getObjectFromString(payload, InteractionEvent.class));
                    break;
                case AgentCampaignRelationEvent.TYPE:
                    processAgentCampaignRelationEvent(JsonMapper.getInstance()
                            .getObjectFromString(payload, AgentCampaignRelationEvent.class));
                    break;
            }
        } catch (JSONException e){
            logger.error(e);
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

    private MitAcdEvent getMitAcdMessageValidated (String message){
        Validate.notNull(message, "MitAcd message string cannot be null");
        MitAcdEvent mitAcdEvent = null;
        try {
            mitAcdEvent = JsonMapper.getInstance().getObjectFromString(message, MitAcdEvent.class);
        } catch (JSONException e) {
            logger.error(e);
        }
        Validate.notNull(mitAcdEvent, "MitAcd event message json mapper cannot be null");
        Validate.notEmpty(mitAcdEvent.getType(), "MitAcd event message type cannot be empty");
        Validate.notEmpty(mitAcdEvent.getPayload(), "MitAcd event message payload cannot be empty");
        return mitAcdEvent;
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
