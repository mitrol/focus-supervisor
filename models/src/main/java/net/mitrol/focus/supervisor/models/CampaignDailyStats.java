package net.mitrol.focus.supervisor.models;

public class CampaignDailyStats {

    private Integer campaignId;
    private CampaignTimeAccumulator lastIntervalTimes;
    private InteractionAccumulator lasIntervalInteractions;
    private CampaignTimeAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    public CampaignDailyStats() {
    }

    public CampaignDailyStats(Integer campaignId, CampaignTimeAccumulator lastIntervalTimes, InteractionAccumulator lasIntervalInteractions, CampaignTimeAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.campaignId = campaignId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lasIntervalInteractions = lasIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public CampaignTimeAccumulator getLastIntervalTimes() {
        return lastIntervalTimes;
    }

    public InteractionAccumulator getLasIntervalInteractions() {
        return lasIntervalInteractions;
    }

    public CampaignTimeAccumulator getDailyTimes() {
        return dailyTimes;
    }

    public InteractionAccumulator getDailyInteractions() {
        return dailyInteractions;
    }
}
