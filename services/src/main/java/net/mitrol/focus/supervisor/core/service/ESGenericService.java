package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.common.event.EventMessage;
import net.mitrol.focus.supervisor.common.enums.WidgetType;
import net.mitrol.focus.supervisor.core.service.domain.ESAgentStateRepository;
import net.mitrol.focus.supervisor.core.service.domain.ESInteractionStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ESGenericService {

    @Autowired
    ESInteractionStatsRepository stateRepository;

    @Autowired
    ESAgentStateRepository esAgentStateRepository;

    public Object getWidgetByMessage(EventMessage eventMessage) {
        WidgetType type = WidgetType.valueOf(eventMessage.getWidgetType().toUpperCase());
        switch (type) {
            case INTERACTION_STATES:
                return countInteractionStats(null, null, null, null, null, null, false);
            case AGENT_STATES:
                return countAgentState(null, null, null, null, false);
            case AUXILIARY_STATES:
                return countAgentState(null, null, null, null, false);
            case STATES_TIME_COUNTER:
                return countAgentState(null, null, null, null, false);
            case AUXILIARY_STATES_TIME_COUNTER:
                return countAgentState(null, null, null, null, false);
            case AGENT_INFORMATION:
                return countAgentState(null, null, null, null, false);
            case INTERACTIONS_COUNTER:
                return countAgentState(null, null, null, null, false);
            case INTERACTIONS_DETAILS_COUNTER:
                return countAgentState(null, null, null, null, false);
            case SKILL_INDICATORS:
                return countAgentState(null, null, null, null, false);
            case AGENT_INTERACTION_STATES:
                return countAgentState(null, null, null, null, false);
            case SKILL_INTERACTIONS_COUNTER:
                return countAgentState(null, null, null, null, false);
            case AGENT_INTERACTIONS_COUNTER:
                return countAgentState(null, null, null, null, false);
            default:
                return null;
        }
    }

    public Map countInteractionStats(String index, String campaignId, String companyId, String groupId,
                                     String agentId, String splitId, boolean searchAllIndex) {
        return stateRepository.countInteractionStats(index, campaignId, companyId, groupId, agentId, splitId, searchAllIndex);
    }

    public List<HashMap> countAgentState(String index, String campaignId, String companyId,
                                         String agentId, boolean searchAllIndex) {
        return esAgentStateRepository.countAgentStatus(index, campaignId, companyId, agentId, searchAllIndex);
    }

}
