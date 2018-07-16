package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.SplitIntervalStats;

import java.io.Serializable;

public class SplitIntervalStatsDTO implements Serializable {

    private String date;
    private SplitIntervalStats splitIntervalStats;

    public SplitIntervalStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SplitIntervalStats getSplitIntervalStats() {
        return splitIntervalStats;
    }

    public void setSplitIntervalStats(SplitIntervalStats splitIntervalStats) {
        this.splitIntervalStats = splitIntervalStats;
    }
}
