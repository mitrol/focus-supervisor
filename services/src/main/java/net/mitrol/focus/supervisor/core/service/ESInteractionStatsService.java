package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.core.service.domain.ESInteractionStatsRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ESInteractionStatsService {

    @Autowired
    ESInteractionStatsRepository stateRepository;

    public List<HashMap> countInteractionStats(String index, String campaignId, String companyId, String groupId,
                                               String agentId, String batchId, boolean searchAllIndex) {
        Validate.notNull(campaignId,  "campaignId must be not null");
        return stateRepository.countInteractionStats(index, campaignId, companyId, groupId, agentId, batchId, searchAllIndex);
    }

}
