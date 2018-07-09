package net.mitrol.focus.supervisor.models;

import java.time.Duration;

public class AgentIntervalStats {

    private Integer agentId;
    private Integer groupId;
    private AgentState agentState;
    private Duration currentState;
    private String extension;
    private String ip;
    private AgentAccumulator agentAccumulator;
    private AgentInternalAccumulator internalOutboundStats;
    private AgentInternalAccumulator internalInboundStats;

    public AgentIntervalStats() {
    }

    public AgentIntervalStats(int agentId, int groupId, AgentState status, Duration currentState, String extension, String ip, AgentAccumulator agentAccumulator, AgentInternalAccumulator internalOutboundStats, AgentInternalAccumulator internalInboundStats) {
        this.agentId = agentId;
        this.groupId = groupId;
        this.agentState = status;
        this.currentState = currentState;
        this.extension = extension;
        this.ip = ip;
        this.agentAccumulator = agentAccumulator;
        this.internalOutboundStats = internalOutboundStats;
        this.internalInboundStats = internalInboundStats;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public Duration getCurrentState() {
        return currentState;
    }

    public String getExtension() {
        return extension;
    }

    public String getIp() {
        return ip;
    }

    public AgentAccumulator getAgentAccumulator() {
        return agentAccumulator;
    }

    public AgentInternalAccumulator getInternalOutboundStats() {
        return internalOutboundStats;
    }

    public AgentInternalAccumulator getInternalInboundStats() {
        return internalInboundStats;
    }
}