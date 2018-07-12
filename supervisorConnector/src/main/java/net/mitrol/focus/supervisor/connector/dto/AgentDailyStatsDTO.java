package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.AgentDailyStats;

import java.io.Serializable;

public class AgentDailyStatsDTO implements Serializable {

    private String date;
    private AgentDailyStats agentDailyStats;

    public AgentDailyStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AgentDailyStats getAgentDailyStats() {
        return agentDailyStats;
    }

    public void setAgentDailyStats(AgentDailyStats agentDailyStats) {
        this.agentDailyStats = agentDailyStats;
    }
}
