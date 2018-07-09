package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;
import net.mitrol.utils.entities.SockMessage;

public class SplitDailyStats {

    private Integer splitId;
    private Integer campaignId;
    private AgentSplitAccumulator lastIntervalTimes;
    private InteractionAccumulator lastIntervalInteractions;
    private AgentSplitAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    private SplitDailyStats(Integer splitId, Integer campaignId, AgentSplitAccumulator lastIntervalTimes, InteractionAccumulator lastIntervalInteractions, AgentSplitAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.splitId = splitId;
        this.campaignId = campaignId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lastIntervalInteractions = lastIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public static SplitDailyStats parse(SockMessage sockMessage) {
        return new SplitDailyStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idc")),
                AgentSplitAccumulator.parse(sockMessage.getString("atn")),
                InteractionAccumulator.parse(sockMessage.getString("aln")),
                AgentSplitAccumulator.parse(sockMessage.getString("atd")),
                InteractionAccumulator.parse(sockMessage.getString("ald")));
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