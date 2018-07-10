package net.mitrol.focus.supervisor.models.webhooks;

import java.util.Date;

public class AgentCampaignLogin {

     private Date timestamp;
     private Integer agentId;
     private Integer campaignId;
     private Integer groupId;

    public AgentCampaignLogin(Date timestamp, Integer agentId, Integer campaignId, Integer groupId) {
        this.timestamp = timestamp;
        this.agentId = agentId;
        this.campaignId = campaignId;
        this.groupId = groupId;
    }

    public AgentCampaignLogin() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
