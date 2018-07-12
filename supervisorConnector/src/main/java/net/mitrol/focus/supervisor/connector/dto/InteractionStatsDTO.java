package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.InteractionStats;

import java.io.Serializable;

public class InteractionStatsDTO implements Serializable {

    private String date;
    private InteractionStats interactionStats;

    public InteractionStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InteractionStats getInteractionStats() {
        return interactionStats;
    }

    public void setInteractionStats(InteractionStats interactionStats) {
        this.interactionStats = interactionStats;
    }
}
