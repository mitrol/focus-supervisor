package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

public class CampaignDailyStats {

    private Integer campaignDailyStatsId;
    private TimeAccumulator lastIntervalTimes;
    private InteractionAccumulator lasIntervalInteractions;
    private TimeAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    private CampaignDailyStats(Integer campaignDailyStatsId, TimeAccumulator lastIntervalTimes, InteractionAccumulator lasIntervalInteractions, TimeAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.campaignDailyStatsId = campaignDailyStatsId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lasIntervalInteractions = lasIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public static CampaignDailyStats parse(SockMessage sockMessage) {
        return new CampaignDailyStats(
                sockMessage.getInteger("id"),
                TimeAccumulator.parse(sockMessage.getString("atn")),
                InteractionAccumulator.parse(sockMessage.getString("aln")),
                TimeAccumulator.parse(sockMessage.getString("atd")),
                InteractionAccumulator.parse(sockMessage.getString("ald")));
    }

    public Integer getCampaignDailyStatsId() {
        return campaignDailyStatsId;
    }

    public TimeAccumulator getLastIntervalTimes() {
        return lastIntervalTimes;
    }

    public InteractionAccumulator getLasIntervalInteractions() {
        return lasIntervalInteractions;
    }

    public TimeAccumulator getDailyTimes() {
        return dailyTimes;
    }

    public InteractionAccumulator getDailyInteractions() {
        return dailyInteractions;
    }
}
