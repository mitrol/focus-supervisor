package net.mitrol.mitct.mitacd.event;

import java.util.Date;

public class AgentCampaignRelationEvent {

    public static final String TYPE = "agent_campaign_relation_event";

    private Date timestamp;
    private int userId;
    private int campaignId;
    private boolean isAssigned;

    public AgentCampaignRelationEvent() {
    }

    public AgentCampaignRelationEvent(Date timestamp, int userId, int campaignId, boolean isAssigned) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.campaignId = campaignId;
        this.isAssigned = isAssigned;
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

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        this.isAssigned = assigned;
    }
}
