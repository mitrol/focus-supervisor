package net.mitrol.focus.supervisor.models.webhooks;

import java.util.Date;

public class AgentCampaignLogout {

    private Date loginTimestamp;
    private Date logoutTimestamp;
    private Integer agentId;
    private Integer campaignId;
    private Integer groupId;
    private Integer duration;

    public AgentCampaignLogout(Date loginTimestamp, Date logoutTimestamp, Integer agentId, Integer campaignId, Integer groupId, Integer duration) {
        this.loginTimestamp = loginTimestamp;
        this.logoutTimestamp = logoutTimestamp;
        this.agentId = agentId;
        this.campaignId = campaignId;
        this.groupId = groupId;
        this.duration = duration;
    }

    public AgentCampaignLogout() {
    }

    public Date getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(Date loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public Date getLogoutTimestamp() {
        return logoutTimestamp;
    }

    public void setLogoutTimestamp(Date logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
