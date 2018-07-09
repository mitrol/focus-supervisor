package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.SplitIntervalStats;
import net.mitrol.utils.entities.SockMessage;

public class SplitIntervalStatsAdapter {
    public static SplitIntervalStats adapt(SockMessage sockMessage) {
        return new SplitIntervalStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idc")),
                AgentSplitAccumulatorAdapter.adapt(sockMessage.getString("ata")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("ala")),
                sockMessage.getBoolean("d"));
    }
}
