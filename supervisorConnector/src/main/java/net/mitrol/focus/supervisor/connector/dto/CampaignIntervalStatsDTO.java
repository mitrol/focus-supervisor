package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.CampaignIntervalStats;

import java.io.Serializable;

public class CampaignIntervalStatsDTO implements Serializable {

    private String date;
    private CampaignIntervalStats campaignIntervalStats;

    public CampaignIntervalStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CampaignIntervalStats getCampaignIntervalStats() {
        return campaignIntervalStats;
    }

    public void setCampaignIntervalStats(CampaignIntervalStats campaignIntervalStats) {
        this.campaignIntervalStats = campaignIntervalStats;
    }
}
