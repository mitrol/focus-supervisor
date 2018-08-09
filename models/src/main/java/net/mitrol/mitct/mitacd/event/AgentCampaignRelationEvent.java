package net.mitrol.mitct.mitacd.event;

import java.util.Date;

public class AgentCampaignRelationEvent {

    public static final String TYPE = "agent_campaign_relation_event";

    private Date timestamp;
    private int userId;
    private int campaignId;
    private boolean assignation;

    public AgentCampaignRelationEvent() {
    }

    public AgentCampaignRelationEvent(Date timestamp, int userId, int campaignId, boolean assignation) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.campaignId = campaignId;
        this.assignation = assignation;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public boolean isAssignation() {
        return assignation;
    }

    public void setAssignation(boolean assignation) {
        this.assignation = assignation;
    }
}
