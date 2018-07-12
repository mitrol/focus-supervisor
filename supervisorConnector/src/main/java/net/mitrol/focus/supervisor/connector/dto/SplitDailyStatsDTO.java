package net.mitrol.focus.supervisor.connector.dto;

import net.mitrol.focus.supervisor.models.SplitDailyStats;

import java.io.Serializable;

public class SplitDailyStatsDTO implements Serializable {

    private String date;
    private SplitDailyStats splitDailyStats;

    public SplitDailyStatsDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SplitDailyStats getSplitDailyStats() {
        return splitDailyStats;
    }

    public void setSplitDailyStats(SplitDailyStats splitDailyStats) {
        this.splitDailyStats = splitDailyStats;
    }
}
