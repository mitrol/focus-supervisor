package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.connector.dto.*;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.utils.DateTimeUtils;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MitAcdMessageService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdMessageService.class);

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

    protected final void kafkaMsgProcess(String message) {
        try {
            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.has("CampaignDailyStats")) {
                    CampaignDailyStats campaignDailyStats = JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("CampaignDailyStats"), CampaignDailyStats.class);
                    generateCampaignDailyStats(campaignDailyStats);
                } else if (obj.has("CampaignIntervalStats")) {
                    CampaignIntervalStats campaignIntervalStats =  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("CampaignIntervalStats"), CampaignIntervalStats.class);
                    generateCampaignIntervalStats(campaignIntervalStats);
                } else if (obj.has("SplitIntervalStats")) {
                    SplitIntervalStats splitIntervalStats =  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("SplitIntervalStats"), SplitIntervalStats.class);
                    generateSplitIntervalStats(splitIntervalStats);
                } else if (obj.has("SplitDailyStats")) {
                    SplitDailyStats splitDailyStats =  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("SplitDailyStats"), SplitDailyStats.class);
                    generateSplitDailyStats(splitDailyStats);
                } else if (obj.has("AgentIntervalStats")) {
                    AgentIntervalStats agentIntervalStats =  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("AgentIntervalStats"), AgentIntervalStats.class);
                    generateAgentIntervalStats(agentIntervalStats);
                } else if (obj.has("AgentDailyStats")) {
                    AgentDailyStats agentDailyStats=  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("AgentDailyStats"), AgentDailyStats.class);
                    generateAgentDailyStats(agentDailyStats);
                } else if (obj.has("InteractionStats")) {
                    InteractionStats interactionStats =  JsonMapper.getInstance().getObjectFromJSON((JSONObject) obj.get("InteractionStats"), InteractionStats.class);
                    generateInteractionStats(interactionStats);
                }
            }
        } catch (JSONException e) {
            logger.error(e, "Json parser error");
        }
    }

    public void generateCampaignDailyStats(CampaignDailyStats campaignDailyStats) {
        CampaignDailyStatsDTO campaignDailyStatsDTO = new CampaignDailyStatsDTO();
        campaignDailyStatsDTO.setDate(getDateNowValue());
        campaignDailyStatsDTO.setCampaignDailyStats(campaignDailyStats);
        esService.buildDocumentIndex(campaignDailyStatsDTO, getIndexDateValue(index_campaign_daily), index_type, "");
    }

    public void generateSplitDailyStats(SplitDailyStats splitDailyStats) {
        SplitDailyStatsDTO splitDailyStatsDTO = new SplitDailyStatsDTO();
        splitDailyStatsDTO.setDate(getDateNowValue());
        splitDailyStatsDTO.setSplitDailyStats(splitDailyStats);
        esService.buildDocumentIndex(splitDailyStatsDTO, getIndexDateValue(index_split_daily), index_type, "");
    }

    public void generateSplitIntervalStats(SplitIntervalStats splitIntervalStats) {
        SplitIntervalStatsDTO splitIntervalStatsDTO = new SplitIntervalStatsDTO();
        splitIntervalStatsDTO.setDate(getDateNowValue());
        splitIntervalStatsDTO.setSplitIntervalStats(splitIntervalStats);
        esService.buildDocumentIndex(splitIntervalStatsDTO, getIndexDateValue(index_split_interval), index_type, "");
    }

    public void generateCampaignIntervalStats(CampaignIntervalStats campaignIntervalStats) {
        CampaignIntervalStatsDTO campaignIntervalStatsDTO = new CampaignIntervalStatsDTO();
        campaignIntervalStatsDTO.setDate(getDateNowValue());
        campaignIntervalStatsDTO.setCampaignIntervalStats(campaignIntervalStats);
        esService.buildDocumentIndex(campaignIntervalStatsDTO, getIndexDateValue(index_campaign_interval), index_type, "");
    }

    public void generateAgentIntervalStats(AgentIntervalStats agentIntervalStats) {
        AgentIntervalStatsDTO agentIntervalStatsDTO = new AgentIntervalStatsDTO();
        agentIntervalStatsDTO.setDate(getDateNowValue());
        agentIntervalStatsDTO.setAgentIntervalStats(agentIntervalStats);
        esService.buildDocumentIndex(agentIntervalStatsDTO, getIndexDateValue(index_agent_interval), index_type, "");
    }

    public void generateAgentDailyStats(AgentDailyStats agentDailyStats) {
        AgentDailyStatsDTO agentDailyStatsDTO = new AgentDailyStatsDTO();
        agentDailyStatsDTO.setDate(getDateNowValue());
        agentDailyStatsDTO.setAgentDailyStats(agentDailyStats);
        esService.buildDocumentIndex(agentDailyStatsDTO, getIndexDateValue(index_agent_daily), index_type, "");
    }

    public void generateInteractionStats(InteractionStats interactionStats) {
        InteractionStatsDTO interactionStatsDTO = new InteractionStatsDTO();
        interactionStatsDTO.setDate(getDateNowValue());
        interactionStatsDTO.setInteractionStats(interactionStats);
        esService.buildDocumentIndex(interactionStatsDTO, getIndexDateValue(index_interaction), index_type, "");
    }

    private String getIndexDateValue (String indexName){
        String today = DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_FORMAT);
        return indexName + "_" + today.replaceAll("/", "-");
    }

    private String getDateNowValue() {
        return DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_HOUR_FORMAT);
    }
}
