package net.mitrol.focus.supervisor.connector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mitrol.ct.api.controllers.responses.AgentProfileResponse;
import net.mitrol.ct.api.controllers.responses.CampaignResponse;
import net.mitrol.ct.api.controllers.responses.ListResponse;
import net.mitrol.ct.api.entities.Group;
import net.mitrol.focus.supervisor.core.service.CTApiClientService;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.utils.json.JsonMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marce on 5/27/18.
 */
@Service
public class ProcessMessageService {

    private final Logger logger = Logger.getLogger("ProcessMessageService");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CTApiClientService ctApiClient;

    @Autowired
    private ESHighLevelClientService esService;

    public void processMessage(String message) {
        try {
            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.toString().contains("campaignDailyStatsId")) {
                    CampaignDailyStats campaignDailyStats = JsonMapper.getInstance().getObjectFromJSON(obj, CampaignDailyStats.class);
                    generateCampaignDailyStats(campaignDailyStats);
                    statusInCampaignDailyStats(campaignDailyStats.getCampaignDailyStatsId().longValue());
                } else if (obj.toString().contains("campaignIntervalStatsId")) {
                    CampaignIntervalStats campaignIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, CampaignIntervalStats.class);
                    generateCampaignIntervalStats(campaignIntervalStats);
                    statusInCampaignDailyStats(campaignIntervalStats.getCampaignIntervalStatsId().longValue());
                } else if (obj.toString().contains("listIntervalStatsId")) {
                    ListIntervalStats listIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, ListIntervalStats.class);
                    generateListIntervalStats(listIntervalStats);
                    statusInLotes(listIntervalStats.getListIntervalStatsId().longValue());
                } else if (obj.toString().contains("listDailyStatsId")) {
                    ListDailyStats listDailyStats =  JsonMapper.getInstance().getObjectFromJSON(obj, ListDailyStats.class);
                    generateListDailyStats(listDailyStats);
                    statusInLotes(listDailyStats.getListDailyStatsId().longValue());
                } else if (obj.toString().contains("agentIntervalStatsId")) {
                    AgentIntervalStats agentIntervalStats =  JsonMapper.getInstance().getObjectFromJSON(obj, AgentIntervalStats.class);
                    generateAgentIntervalStats(agentIntervalStats);
                    statusInAgents(agentIntervalStats.getAgentIntervalStatsId().longValue());
                } else if (obj.toString().contains("agentDailyStatsId")) {
                    AgentDailyStats agentDailyStats=  JsonMapper.getInstance().getObjectFromJSON(obj, AgentDailyStats.class);
                    generateAgentDailyStats(agentDailyStats);
                    statusInAgents(agentDailyStats.getAgentDailyStatsId().longValue());
                } else if (obj.toString().contains("interactionStatsId")) {
                    // Todavia no se si pertenece al grupo
                    InteractionStats interactionStats =  JsonMapper.getInstance().getObjectFromJSON(obj, InteractionStats.class);
                    generateInteractionStats(interactionStats);
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

    private void generateCampaignDailyStats(CampaignDailyStats campaignDailyStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String campaignDailyId = objectMapper.writeValueAsString(campaignDailyStats.getCampaignDailyStatsId());

            final String lastIntervalTimes = objectMapper.writeValueAsString(campaignDailyStats.getLastIntervalTimes());
            final String lasIntervalInteractions = objectMapper.writeValueAsString(campaignDailyStats.getLasIntervalInteractions());
            final String dailyTimes = objectMapper.writeValueAsString(campaignDailyStats.getDailyTimes());
            final String dailyInteractions = objectMapper.writeValueAsString(campaignDailyStats.getLasIntervalInteractions());

            data.put("campaignDailyStatsId", campaignDailyId);
            //data.put("lastIntervalTimes", JsonMapper.getInstance().getStringJsonFromObject(campaignDailyStats.getLastIntervalTimes()));
            //data.put("lasIntervalInteractions", JsonMapper.getInstance().getStringJsonFromObject(campaignDailyStats.getLasIntervalInteractions()));
            data.put("lastIntervalTimes", lastIntervalTimes);
            data.put("lasIntervalInteractions", lasIntervalInteractions);
            data.put("dailyTimes", dailyTimes);
            data.put("dailyInteractions", dailyInteractions);
            esService.buildIndexByParam(data, "mitrol-model", "campaign-daily-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateListDailyStats(ListDailyStats listDailyStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String listDailyId = objectMapper.writeValueAsString(listDailyStats.getListDailyStatsId());
            final String lastIntervalTimes = objectMapper.writeValueAsString(listDailyStats.getLastIntervalTimes());
            final String lasIntervalInteractions = objectMapper.writeValueAsString(listDailyStats.getLasIntervalInteractions());
            final String dailyTimes = objectMapper.writeValueAsString(listDailyStats.getDailyTimes());
            final String dailyInteractions = objectMapper.writeValueAsString(listDailyStats.getLasIntervalInteractions());

            data.put("listDailyStatsId", listDailyId);
            data.put("lastIntervalTimes", lastIntervalTimes);
            data.put("lasIntervalInteractions", lasIntervalInteractions);
            data.put("dailyTimes", dailyTimes);
            data.put("dailyInteractions", dailyInteractions);
            esService.buildIndexByParam(data, "mitrol-model", "list-daily-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateListIntervalStats(ListIntervalStats listIntervalStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String listIntervalStatsId = objectMapper.writeValueAsString(listIntervalStats.getListIntervalStatsId());
            final String times = objectMapper.writeValueAsString(listIntervalStats.getTimes());
            final String iInteractions = objectMapper.writeValueAsString(listIntervalStats.getiInteractions());
            final String canBeDialed = objectMapper.writeValueAsString(listIntervalStats.getCanBeDialed());

            data.put("listIntervalStatsId", listIntervalStatsId);
            data.put("times", times);
            data.put("iInteractions", iInteractions);
            data.put("canBeDialed", canBeDialed);
            esService.buildIndexByParam(data, "mitrol-model", "list-interval-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateCampaignIntervalStats(CampaignIntervalStats campaignIntervalStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String campaignIntervalStatsId = objectMapper.writeValueAsString(campaignIntervalStats.getCampaignIntervalStatsId());
            final String times = objectMapper.writeValueAsString(campaignIntervalStats.getTimes());
            final String interactions = objectMapper.writeValueAsString(campaignIntervalStats.getInteractions());
            final String queued = objectMapper.writeValueAsString(campaignIntervalStats.getQueued());
            final String waitingTime = objectMapper.writeValueAsString(campaignIntervalStats.getWaitingTime());
            final String serviceLevel = objectMapper.writeValueAsString(campaignIntervalStats.getServiceLevel());
            final String averageSpeedOfAnswer = objectMapper.writeValueAsString(campaignIntervalStats.getAverageSpeedOfAnswer());
            final String impatience = objectMapper.writeValueAsString(campaignIntervalStats.getImpatience());
            final String liveVoiceProbability = objectMapper.writeValueAsString(campaignIntervalStats.getLiveVoiceProbability());
            final String estimatedWaitingTime = objectMapper.writeValueAsString(campaignIntervalStats.getEstimatedWaitingTime());
            final String averageHandlingTime = objectMapper.writeValueAsString(campaignIntervalStats.getAverageHandlingTime());
            final String averageTalkingTime = objectMapper.writeValueAsString(campaignIntervalStats.getAverageTalkingTime());

            data.put("campaignIntervalStatsId", campaignIntervalStatsId);
            data.put("times", times);
            data.put("interactions", interactions);
            data.put("queued", queued);
            data.put("waitingTime", waitingTime);
            data.put("serviceLevel", serviceLevel);
            data.put("averageSpeedOfAnswer", averageSpeedOfAnswer);
            data.put("impatience", impatience);
            data.put("liveVoiceProbability", liveVoiceProbability);
            data.put("estimatedWaitingTime", estimatedWaitingTime);
            data.put("averageHandlingTime", averageHandlingTime);
            data.put("averageTalkingTime", averageTalkingTime);
            esService.buildIndexByParam(data, "mitrol-model", "campaign-interval-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateAgentIntervalStats(AgentIntervalStats agentIntervalStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String agentIntervalStatsId = objectMapper.writeValueAsString(agentIntervalStats.getAgentIntervalStatsId());
            final String agentState = objectMapper.writeValueAsString(agentIntervalStats.getAgentState());
            final String currentState = objectMapper.writeValueAsString(agentIntervalStats.getCurrentState());
            final String extension = objectMapper.writeValueAsString(agentIntervalStats.getExtension());
            final String ip = objectMapper.writeValueAsString(agentIntervalStats.getIp());
            final String agentAccumulator = objectMapper.writeValueAsString(agentIntervalStats.getAgentAccumulator());
            final String internalOutboundStats = objectMapper.writeValueAsString(agentIntervalStats.getInternalOutboundStats());
            final String internalInboundStats = objectMapper.writeValueAsString(agentIntervalStats.getInternalInboundStats());

            data.put("agentIntervalStatsId", agentIntervalStatsId);
            data.put("agentState", agentState);
            data.put("currentState", currentState);
            data.put("extension", extension);
            data.put("ip", ip);
            data.put("agentAccumulator", agentAccumulator);
            data.put("internalOutboundStats", internalOutboundStats);
            data.put("internalInboundStats", internalInboundStats);
            esService.buildIndexByParam(data, "mitrol-model", "agent-interval-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateAgentDailyStats(AgentDailyStats agentDailyStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String agentDailyStatsId = objectMapper.writeValueAsString(agentDailyStats.getAgentDailyStatsId());
            final String lastIntervalStats = objectMapper.writeValueAsString(agentDailyStats.getLastIntervalStats());
            final String lastIntervalInternalOutboundStats = objectMapper.writeValueAsString(agentDailyStats.getLastIntervalInternalOutboundStats());
            final String lastIntervalInternalInboundStats = objectMapper.writeValueAsString(agentDailyStats.getLastIntervalInternalInboundStats());
            final String dailyStats = objectMapper.writeValueAsString(agentDailyStats.getDailyStats());
            final String dailyOutboundStats = objectMapper.writeValueAsString(agentDailyStats.getDailyOutboundStats());
            final String dailyInboundStats = objectMapper.writeValueAsString(agentDailyStats.getDailyInboundStats());

            data.put("agentDailyStatsId", agentDailyStatsId);
            data.put("lastIntervalStats", lastIntervalStats);
            data.put("lastIntervalInternalOutboundStats", lastIntervalInternalOutboundStats);
            data.put("lastIntervalInternalInboundStats", lastIntervalInternalInboundStats);
            data.put("dailyStats", dailyStats);
            data.put("dailyOutboundStats", dailyOutboundStats);
            data.put("dailyInboundStats", dailyInboundStats);
            esService.buildIndexByParam(data, "mitrol-model", "agent-daily-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void generateInteractionStats(InteractionStats interactionStats) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();

            final String interactionStatsId = objectMapper.writeValueAsString(interactionStats.getInteractionStatsId());
            final String remoteAgentId = objectMapper.writeValueAsString(interactionStats.getRemoteAgentId());
            final String campaignId = objectMapper.writeValueAsString(interactionStats.getCampaignId());
            final String listId = objectMapper.writeValueAsString(interactionStats.getListId());
            final String interactionType = objectMapper.writeValueAsString(interactionStats.getInteractionType());
            final String contactType = objectMapper.writeValueAsString(interactionStats.getContactType());
            final String state = objectMapper.writeValueAsString(interactionStats.getState());
            final String duration = objectMapper.writeValueAsString(interactionStats.getDuration());
            final String remoteParty = objectMapper.writeValueAsString(interactionStats.getRemoteParty());
            final String interactionId = objectMapper.writeValueAsString(interactionStats.getInteractionId());
            final String segment = objectMapper.writeValueAsString(interactionStats.getSegment());
            final String recordingCriterionId = objectMapper.writeValueAsString(interactionStats.getRecordingCriterionId());
            final String active = objectMapper.writeValueAsString(interactionStats.isActive());
            final String activeInOrigin = objectMapper.writeValueAsString(interactionStats.isActiveInOrigin());
            final String queueTime = objectMapper.writeValueAsString(interactionStats.getQueueTime());
            final String totalQueueTime = objectMapper.writeValueAsString(interactionStats.getTotalQueueTime());
            final String talking = objectMapper.writeValueAsString(interactionStats.getTalking());
            final String detectingSpeech = objectMapper.writeValueAsString(interactionStats.isDetectingSpeech());
            final String detectingEmotion = objectMapper.writeValueAsString(interactionStats.isDetectingEmotion());
            final String localWordSpotting = objectMapper.writeValueAsString(interactionStats.isLocalWordSpotting());
            final String localGender = objectMapper.writeValueAsString(interactionStats.getLocalGender());
            final String remoteWordSpotting = objectMapper.writeValueAsString(interactionStats.isRemoteWordSpotting());
            final String remoteGender = objectMapper.writeValueAsString(interactionStats.getRemoteGender());

            data.put("interactionStatsId", interactionStatsId);
            data.put("remoteAgentId", remoteAgentId);
            data.put("campaignId", campaignId);
            data.put("listId", listId);
            data.put("interactionType", interactionType);
            data.put("contactType", contactType);
            data.put("state", state);
            data.put("duration", duration);
            data.put("remoteParty", remoteParty);
            data.put("interactionId", interactionId);
            data.put("segment", segment);
            data.put("recordingCriterionId", recordingCriterionId);
            data.put("active", active);
            data.put("activeInOrigin", activeInOrigin);
            data.put("queueTime", queueTime);
            data.put("totalQueueTime", totalQueueTime);
            data.put("talking", talking);
            data.put("detectingSpeech", detectingSpeech);
            data.put("detectingEmotion", detectingEmotion);
            data.put("localWordSpotting", localWordSpotting);
            data.put("localGender", localGender);
            data.put("remoteWordSpotting", remoteWordSpotting);
            data.put("remoteGender", remoteGender);
            esService.buildIndexByParam(data, "mitrol-model", "interaction-stats", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}