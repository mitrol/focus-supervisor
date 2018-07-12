package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.CampaignDailyStats;

import java.io.Serializable;

public class CampaignDailyStatsDTO implements Serializable {

    private String date;
    private CampaignDailyStats campaignDailyStats;

    public CampaignDailyStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CampaignDailyStats getCampaignDailyStats() {
        return campaignDailyStats;
    }

    public void setCampaignDailyStats(CampaignDailyStats campaignDailyStats) {
        this.campaignDailyStats = campaignDailyStats;
    }
}
