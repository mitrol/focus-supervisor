package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

public class ListIntervalStats {

    private Integer listIntervalStatsId;
    private TimeAccumulator times;
    private InteractionAccumulator iInteractions;
    private Boolean canBeDialed;

    private ListIntervalStats(Integer listIntervalStatsId, TimeAccumulator times, InteractionAccumulator iInteractions, Boolean canBeDialed) {
        this.listIntervalStatsId = listIntervalStatsId;
        this.times = times;
        this.iInteractions = iInteractions;
        this.canBeDialed = canBeDialed;
    }

    public static ListIntervalStats parse(SockMessage sockMessage) {
        return new ListIntervalStats(
                sockMessage.getInteger("id"),
                TimeAccumulator.parse(sockMessage.getString("ata")),
                InteractionAccumulator.parse(sockMessage.getString("ala")),
                sockMessage.getBoolean("d"));
    }
}