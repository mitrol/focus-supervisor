package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by marce on 5/27/18.
 */
@Service
public class ESModelService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(ESModelService.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_COMPLETE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter TRANSFORM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    @Value("${index.list.daily.stats}")
    private String index_list_daily;

    @Value("${index.list.interval.stats}")
    private String index_list_interval;

    @Value("${index.interaction.stats}")
    private String index_interaction;

    @Autowired
    private ESHighLevelClientService esService;

    public void generateCampaignDailyStats(CampaignDailyStats campaignDailyStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(campaignDailyStats, index_campaign_daily + date, index_type, "");
    }

    public void generateListDailyStats(ListDailyStats listDailyStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(listDailyStats, index_list_daily + date, index_type, "");
    }

    public void generateListIntervalStats(ListIntervalStats listIntervalStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(listIntervalStats, index_list_interval + date, index_type, "");
    }

    public void generateCampaignIntervalStats(CampaignIntervalStats campaignIntervalStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(campaignIntervalStats, index_campaign_interval + date, index_type, "");
    }

    public void generateAgentIntervalStats(AgentIntervalStats agentIntervalStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(agentIntervalStats, index_agent_interval + date, index_type, "");
    }

    public void generateAgentDailyStats(AgentDailyStats agentDailyStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(agentDailyStats, index_agent_daily + date, index_type, "");
    }

    public void generateInteractionStats(InteractionStats interactionStats) {
        LocalDateTime schedule = LocalDateTime.now();
        String date = schedule.format(TRANSFORM_FORMATTER);
        esService.buildDocumentIndex(interactionStats, index_interaction + date, index_type, "");
    }
}
