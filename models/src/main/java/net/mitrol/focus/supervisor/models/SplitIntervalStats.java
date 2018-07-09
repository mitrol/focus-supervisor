package net.mitrol.focus.supervisor.models;

public class SplitIntervalStats {

    private Integer splitId;
    private Integer campaignId;
    private AgentSplitAccumulator times;
    private InteractionAccumulator interactions;
    private Boolean canBeDialed;

    public SplitIntervalStats() {
    }

    public SplitIntervalStats(Integer splitId, Integer campaignId, AgentSplitAccumulator times, InteractionAccumulator interactions, Boolean canBeDialed) {
        this.splitId = splitId;
        this.campaignId = campaignId;
        this.times = times;
        this.interactions = interactions;
        this.canBeDialed = canBeDialed;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public AgentSplitAccumulator getTimes() {
        return times;
    }

    public InteractionAccumulator getInteractions() {
        return interactions;
    }

    public Boolean getCanBeDialed() {
        return canBeDialed;
    }
}