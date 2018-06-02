package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.ct.api.controllers.responses.AgentProfileResponse;
import net.mitrol.ct.api.controllers.responses.CampaignResponse;
import net.mitrol.ct.api.controllers.responses.ListResponse;
import net.mitrol.ct.api.entities.Group;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.focus.supervisor.core.service.impl.ElasticSearchService;
import net.mitrol.utils.json.JsonMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marce on 5/27/18.
 */
@Service
public class ProcessMessageService {

    private final Logger logger = Logger.getLogger("ProcessMessageService");

    @Autowired
    private CTApiClientService ctApiClient;

    @Autowired
    private ElasticSearchService esService;

    public void processMessage(String message) {
        try {
            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.toString().contains("campaignDailyStatsId")) {
                    CampaignDailyStats campaignDailyStats = JsonMapper.getInstance().getObjectFromJSON(obj, CampaignDailyStats.class);
                    statusInCampaignDailyStats(campaignDailyStats.getCampaignDailyStatsId().longValue());
                } else if (obj.toString().contains("campaignIntervalStatsId")) {
                    CampaignIntervalStats campaignIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, CampaignIntervalStats.class);
                    statusInCampaignDailyStats(campaignIntervalStats.getCampaignIntervalStatsId().longValue());
                } else if (obj.toString().contains("listIntervalStatsId")) {
                    ListIntervalStats listIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, ListIntervalStats.class);
                    statusInLotes(listIntervalStats.getListIntervalStatsId().longValue());
                } else if (obj.toString().contains("listDailyStatsId")) {
                    ListDailyStats listDailyStats =  JsonMapper.getInstance().getObjectFromJSON(obj, ListDailyStats.class);
                    statusInLotes(listDailyStats.getListDailyStatsId().longValue());
                } else if (obj.toString().contains("agentIntervalStatsId")) {
                    AgentIntervalStats agentIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, AgentIntervalStats.class);
                    statusInAgents(agentIntervalStats.getAgentIntervalStatsId().longValue());
                } else if (obj.toString().contains("agentDailyStatsId")) {
                    AgentDailyStats agentDailyStats=  JsonMapper.getInstance().getObjectFromJSON(obj, AgentDailyStats.class);
                    statusInAgents(agentDailyStats.getAgentDailyStatsId().longValue());
                } else if (obj.toString().contains("interactionStatsId")) {
                    // Todavia no se si pertenece al grupo
                    InteractionStats interactionStats =  JsonMapper.getInstance().getObjectFromJSON(obj, InteractionStats.class);
                    statusInGroup(interactionStats.getInteractionStatsId().longValue());
                }
            }
        } catch (JSONException jsonE) {
            logger.log(Level.WARNING, jsonE.getMessage());
        }
    }

    private void statusInCampaignDailyStats(Long id) {
        CampaignResponse campaignResponse = ctApiClient.getCampaignById(id);
        logger.info("Get Campaign");
    }

    private void statusInAgents(Long id) {
        List<AgentProfileResponse> agentProfileResponse = ctApiClient.getAgentProfilesById(id);
        logger.info("Get Agents");
    }

    private void statusInLotes(Long id) {
        List<ListResponse> listResponse = ctApiClient.getLotesById(id);
        logger.info("Get Lotes");
    }

    private void statusInGroup(Long id) {
        Group group = ctApiClient.getGroupById(id);
        logger.info("Get Group");
    }
}