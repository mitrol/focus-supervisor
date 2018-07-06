package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

public class AgentDailyStats {

    private Integer agentDailyStatsId;
    private AgentAccumulator lastIntervalStats;
    private AgentInternalAccumulator lastIntervalInternalOutboundStats;
    private AgentInternalAccumulator lastIntervalInternalInboundStats;
    private AgentAccumulator dailyStats;
    private AgentInternalAccumulator dailyOutboundStats;
    private AgentInternalAccumulator dailyInboundStats;

    public AgentDailyStats() {
    }

    private AgentDailyStats(Integer agentDailyStatsId, AgentAccumulator lastIntervalStats, AgentInternalAccumulator lastIntervalInternalOutboundStats, AgentInternalAccumulator lastIntervalInternalInboundStats, AgentAccumulator dailyStats, AgentInternalAccumulator dailyOutboundStats, AgentInternalAccumulator dailyInboundStats) {
        this.agentDailyStatsId = agentDailyStatsId;
        this.lastIntervalStats = lastIntervalStats;
        this.lastIntervalInternalOutboundStats = lastIntervalInternalOutboundStats;
        this.lastIntervalInternalInboundStats = lastIntervalInternalInboundStats;
        this.dailyStats = dailyStats;
        this.dailyOutboundStats = dailyOutboundStats;
        this.dailyInboundStats = dailyInboundStats;
    }

    public static AgentDailyStats parse(SockMessage sockMessage) {
        return new AgentDailyStats(
                sockMessage.getInteger("id"),
                AgentAccumulator.parse(sockMessage.getString("atn")),
                AgentInternalAccumulator.parse(sockMessage.getString("isn")),
                AgentInternalAccumulator.parse(sockMessage.getString("ien")),
                AgentAccumulator.parse(sockMessage.getString("atd")),
                AgentInternalAccumulator.parse(sockMessage.getString("isd")),
                AgentInternalAccumulator.parse(sockMessage.getString("ied")));
    }

    public Integer getAgentDailyStatsId() {
        return agentDailyStatsId;
    }

    public AgentAccumulator getLastIntervalStats() {
        return lastIntervalStats;
    }

    public AgentInternalAccumulator getLastIntervalInternalOutboundStats() {
        return lastIntervalInternalOutboundStats;
    }

    public AgentInternalAccumulator getLastIntervalInternalInboundStats() {
        return lastIntervalInternalInboundStats;
    }

    public AgentAccumulator getDailyStats() {
        return dailyStats;
    }

    public AgentInternalAccumulator getDailyOutboundStats() {
        return dailyOutboundStats;
    }

    public AgentInternalAccumulator getDailyInboundStats() {
        return dailyInboundStats;
    }
}