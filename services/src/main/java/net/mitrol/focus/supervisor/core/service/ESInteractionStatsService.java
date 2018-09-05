package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.core.service.domain.ESAgentStateRepository;
import net.mitrol.focus.supervisor.core.service.domain.ESInteractionStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ESInteractionStatsService {

    @Autowired
    ESInteractionStatsRepository stateRepository;

    @Autowired
    ESAgentStateRepository esAgentStateRepository;

    public Map countInteractionStats(String index, String campaignId, String companyId, String groupId,
                                     String agentId, String splitId, boolean searchAllIndex) {
        return stateRepository.countInteractionStats(index, campaignId, companyId, groupId, agentId, splitId, searchAllIndex);
    }

    public List<HashMap> countAgentState(String index, String campaignId, String companyId,
                                         String agentId, boolean searchAllIndex) {
        return esAgentStateRepository.countAgentStatus(index, campaignId, companyId, agentId, searchAllIndex);
    }

}
