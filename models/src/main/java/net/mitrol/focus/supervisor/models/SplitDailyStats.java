package net.mitrol.focus.supervisor.models;

public class SplitDailyStats {

    private Integer splitId;
    private Integer campaignId;
    private AgentSplitAccumulator lastIntervalTimes;
    private InteractionAccumulator lastIntervalInteractions;
    private AgentSplitAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    public SplitDailyStats(Integer splitId, Integer campaignId, AgentSplitAccumulator lastIntervalTimes, InteractionAccumulator lastIntervalInteractions, AgentSplitAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.splitId = splitId;
        this.campaignId = campaignId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lastIntervalInteractions = lastIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public AgentSplitAccumulator getLastIntervalTimes() {
        return lastIntervalTimes;
    }

    public InteractionAccumulator getLastIntervalInteractions() {
        return lastIntervalInteractions;
    }

    public AgentSplitAccumulator getDailyTimes() {
        return dailyTimes;
    }

    public InteractionAccumulator getDailyInteractions() {
        return dailyInteractions;
    }
}