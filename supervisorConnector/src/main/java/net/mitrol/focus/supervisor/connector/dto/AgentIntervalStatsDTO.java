package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.AgentIntervalStats;

import java.io.Serializable;

public class AgentIntervalStatsDTO implements Serializable {

    private String date;
    private AgentIntervalStats agentIntervalStats;

    public AgentIntervalStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AgentIntervalStats getAgentIntervalStats() {
        return agentIntervalStats;
    }

    public void setAgentIntervalStats(AgentIntervalStats agentIntervalStats) {
        this.agentIntervalStats = agentIntervalStats;
    }
}
