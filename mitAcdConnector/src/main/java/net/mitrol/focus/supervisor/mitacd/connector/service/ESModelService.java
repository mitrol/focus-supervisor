package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.utils.DateTimeUtils;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Created by marce on 5/27/18.
 */
@Service
public class ESModelService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(ESModelService.class);

    @Value("${index.type}")
    private String index_type;

    @Value("${index.campaign.daily.stats}")
    private String index_campaign_daily;

    @Value("${index.campaign.interval.stats}")
    private String index_campaign_interval;

    @Value("${index.agent.daily.stats}")
    private String index_agent_daily;

    @Value("${index.agent.interval.stats}")
    private String index_agent_interval;

    @Value("${index.split.daily.stats}")
    private String index_split_daily;

    @Value("${index.split.interval.stats}")
    private String index_split_interval;

    @Value("${index.interaction.stats}")
    private String index_interaction;

    @Autowired
    private ESHighLevelClientService esService;

    public void generateCampaignDailyStats(CampaignDailyStats campaignDailyStats) {
        esService.buildDocumentIndex(campaignDailyStats, getIndexDateValue(index_campaign_daily), index_type, "");
    }

    public void generateSplitDailyStats(SplitDailyStats SplitDailyStats) {
        esService.buildDocumentIndex(SplitDailyStats, getIndexDateValue(index_split_daily), index_type, "");
    }

    public void generateSplitIntervalStats(SplitIntervalStats splitIntervalStats) {
        esService.buildDocumentIndex(splitIntervalStats, getIndexDateValue(index_split_interval), index_type, "");
    }

    public void generateCampaignIntervalStats(CampaignIntervalStats campaignIntervalStats) {
        esService.buildDocumentIndex(campaignIntervalStats, getIndexDateValue(index_campaign_interval), index_type, "");
    }

    public void generateAgentIntervalStats(AgentIntervalStats agentIntervalStats) {
        esService.buildDocumentIndex(agentIntervalStats, getIndexDateValue(index_agent_interval), index_type, "");
    }

    public void generateAgentDailyStats(AgentDailyStats agentDailyStats) {
        esService.buildDocumentIndex(agentDailyStats, getIndexDateValue(index_agent_daily), index_type, "");
    }

    public void generateInteractionStats(InteractionStats interactionStats) {
        esService.buildDocumentIndex(interactionStats, getIndexDateValue(index_interaction), index_type, "");
    }

    private String getIndexDateValue (String indexName){
        String today = DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_FORMAT);
        return indexName + "-" + today;
    }
}
