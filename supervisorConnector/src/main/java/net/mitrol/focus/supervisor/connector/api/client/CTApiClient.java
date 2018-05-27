package net.mitrol.focus.supervisor.connector.api.client;

import java.util.List;

import feign.Param;
import feign.RequestLine;
import net.mitrol.ct.api.controllers.responses.AgentProfileResponse;
import net.mitrol.ct.api.controllers.responses.CampaignResponse;
import net.mitrol.ct.api.controllers.responses.ListResponse;
import net.mitrol.ct.api.entities.Group;

public interface CTApiClient {
  
  @RequestLine("GET /campaigns")
  List<CampaignResponse> getCampaigns();

  @RequestLine("GET /campaigns?id={campaignId}")
  CampaignResponse getCampaignById(@Param("campaignId") Long campaignId);

  @RequestLine("GET /profiles?id={agentId}")
  List<AgentProfileResponse> getAgentProfilesById(@Param("agentId") Long agentId);

  @RequestLine("GET /batch?id={loteId}")
  List<ListResponse> getLotesById(@Param("loteId") Long loteId);

  @RequestLine("GET /groups?id={groupId}")
  Group getGroupById(@Param("groupId") Long groupId);
}
