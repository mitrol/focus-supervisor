package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

public class ListDailyStats {

    private Integer listDailyStatsId;
    private TimeAccumulator lastIntervalTimes;
    private InteractionAccumulator lasIntervalInteractions;
    private TimeAccumulator dailyTimes;
    private InteractionAccumulator dailyInteractions;

    private ListDailyStats(Integer listDailyStatsId, TimeAccumulator lastIntervalTimes, InteractionAccumulator lasIntervalInteractions, TimeAccumulator dailyTimes, InteractionAccumulator dailyInteractions) {
        this.listDailyStatsId = listDailyStatsId;
        this.lastIntervalTimes = lastIntervalTimes;
        this.lasIntervalInteractions = lasIntervalInteractions;
        this.dailyTimes = dailyTimes;
        this.dailyInteractions = dailyInteractions;
    }

    public static ListDailyStats parse(SockMessage sockMessage) {
        return new ListDailyStats(
                sockMessage.getInteger("id"),
                TimeAccumulator.parse(sockMessage.getString("atn")),
                InteractionAccumulator.parse(sockMessage.getString("aln")),
                TimeAccumulator.parse(sockMessage.getString("atd")),
                InteractionAccumulator.parse(sockMessage.getString("ald")));
    }

    public Integer getListDailyStatsId() {
        return listDailyStatsId;
    }

    public TimeAccumulator getLastIntervalTimes() {
        return lastIntervalTimes;
    }

    public InteractionAccumulator getLasIntervalInteractions() {
        return lasIntervalInteractions;
    }

    public TimeAccumulator getDailyTimes() {
        return dailyTimes;
    }

    public InteractionAccumulator getDailyInteractions() {
        return dailyInteractions;
    }
}