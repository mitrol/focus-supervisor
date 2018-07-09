package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

public class CampaignDailyStats {

    private Integer campaignId;
    private CampaignTimeAccumulator lastIntervalTimes;
    private InteractionAccumulator lasIntervalInteractions;
    private CampaignTimeAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    public CampaignDailyStats() {
    }

    private CampaignDailyStats(Integer campaignId, CampaignTimeAccumulator lastIntervalTimes, InteractionAccumulator lasIntervalInteractions, CampaignTimeAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.campaignId = campaignId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lasIntervalInteractions = lasIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public static CampaignDailyStats parse(SockMessage sockMessage) {
        return new CampaignDailyStats(
                sockMessage.getInteger("id"),
                CampaignTimeAccumulator.parse(sockMessage.getString("atn")),
                InteractionAccumulator.parse(sockMessage.getString("aln")),
                CampaignTimeAccumulator.parse(sockMessage.getString("atd")),
                InteractionAccumulator.parse(sockMessage.getString("ald")));
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
