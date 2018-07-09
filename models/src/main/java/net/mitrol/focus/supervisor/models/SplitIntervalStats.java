package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;
import net.mitrol.utils.entities.SockMessage;

public class SplitIntervalStats {

    private Integer splitId;
    private Integer campaignId;
    private AgentSplitAccumulator times;
    private InteractionAccumulator interactions;
    private Boolean canBeDialed;

    public SplitIntervalStats() {
    }

    private SplitIntervalStats(Integer splitId, Integer campaignId, AgentSplitAccumulator times, InteractionAccumulator interactions, Boolean canBeDialed) {
        this.splitId = splitId;
        this.campaignId = campaignId;
        this.times = times;
        this.interactions = interactions;
        this.canBeDialed = canBeDialed;
    }

    public static SplitIntervalStats parse(SockMessage sockMessage) {
        return new SplitIntervalStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idc")),
                AgentSplitAccumulator.parse(sockMessage.getString("ata")),
                InteractionAccumulator.parse(sockMessage.getString("ala")),
                sockMessage.getBoolean("d"));
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