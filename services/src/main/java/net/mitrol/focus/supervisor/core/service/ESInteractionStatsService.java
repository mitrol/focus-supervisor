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

    public List<HashMap> countInteractionStatsByCampaign(String index, String campaignId) {
        Validate.notNull(index, "index name must be not null");
        Validate.notNull(campaignId,  "campaignId must be not null");
        return stateRepository.countInteractionStatsByCampaign(index, campaignId);
    }

}
