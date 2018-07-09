package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.AgentDailyStats;
import net.mitrol.utils.entities.SockMessage;

public class AgentDailyStatsAdapter {
    public static AgentDailyStats adapt(SockMessage sockMessage) {
        return new AgentDailyStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idg")),
                AgentAccumulatorAdapter.adapt(sockMessage.getString("atn")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("isn")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("ien")),
                AgentAccumulatorAdapter.adapt(sockMessage.getString("atd")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("isd")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("ied")));
    }
}
