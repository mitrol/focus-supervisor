package net.mitrol.focus.supervisor.models;

public class AgentDailyStats {

    private Integer agentId;
    private Integer groupId;
    private AgentAccumulator lastIntervalStats;
    private AgentInternalAccumulator lastIntervalInternalOutboundStats;
    private AgentInternalAccumulator lastIntervalInternalInboundStats;
    private AgentAccumulator dailyStats;
    private AgentInternalAccumulator dailyOutboundStats;
    private AgentInternalAccumulator dailyInboundStats;

    public AgentDailyStats() {
    }

    public AgentDailyStats(Integer agentId, Integer groupId, AgentAccumulator lastIntervalStats, AgentInternalAccumulator lastIntervalInternalOutboundStats, AgentInternalAccumulator lastIntervalInternalInboundStats, AgentAccumulator dailyStats, AgentInternalAccumulator dailyOutboundStats, AgentInternalAccumulator dailyInboundStats) {
        this.agentId = agentId;
        this.groupId = groupId;
        this.lastIntervalStats = lastIntervalStats;
        this.lastIntervalInternalOutboundStats = lastIntervalInternalOutboundStats;
        this.lastIntervalInternalInboundStats = lastIntervalInternalInboundStats;
        this.dailyStats = dailyStats;
        this.dailyOutboundStats = dailyOutboundStats;
        this.dailyInboundStats = dailyInboundStats;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public Integer getGroupId() {
        return groupId;
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