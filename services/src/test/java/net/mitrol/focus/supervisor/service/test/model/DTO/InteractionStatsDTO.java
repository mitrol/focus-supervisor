package net.mitrol.focus.supervisor.service.test.model.DTO;

import net.mitrol.focus.supervisor.models.InteractionStats;

public class InteractionStatsDTO {

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
