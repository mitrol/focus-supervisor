package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.SplitDailyStats;
import net.mitrol.utils.entities.SockMessage;

public class SplitDailyStatsAdapter {
    public static SplitDailyStats adapt(SockMessage sockMessage) {
        return new SplitDailyStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idc")),
                AgentSplitAccumulatorAdapter.adapt(sockMessage.getString("atn")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("aln")),
                AgentSplitAccumulatorAdapter.adapt(sockMessage.getString("atd")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("ald")));
    }
}
