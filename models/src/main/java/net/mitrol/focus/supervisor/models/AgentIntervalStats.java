package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;
import net.mitrol.utils.entities.SockMessage;

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

    private AgentIntervalStats(int agentId, int groupId, AgentState status, Duration currentState, String extension, String ip, AgentAccumulator agentAccumulator, AgentInternalAccumulator internalOutboundStats, AgentInternalAccumulator internalInboundStats) {
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

    public static AgentIntervalStats parse(SockMessage sockMessage) {
        return new AgentIntervalStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idg")),
                AgentState.getFromCode(sockMessage.getInteger("st")),
                sockMessage.getInteger("Tce") == null ? null : Duration.ofSeconds(sockMessage.getInteger("Tce")),
                sockMessage.getString("Ext"),
                sockMessage.getString("IP"),
                AgentAccumulator.parse(sockMessage.getString("ata")),
                AgentInternalAccumulator.parse(sockMessage.getString("isa")),
                AgentInternalAccumulator.parse(sockMessage.getString("iea")));
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