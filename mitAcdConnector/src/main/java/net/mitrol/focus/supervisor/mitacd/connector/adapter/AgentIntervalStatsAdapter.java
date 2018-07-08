package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.AgentIntervalStats;
import net.mitrol.focus.supervisor.models.AgentState;
import net.mitrol.utils.entities.SockMessage;

import java.time.Duration;

public class AgentIntervalStatsAdapter {
    public static AgentIntervalStats adapt(SockMessage sockMessage) {
        return new AgentIntervalStats(
                sockMessage.getInteger("id"),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idg")),
                AgentState.getFromCode(sockMessage.getInteger("st")),
                sockMessage.getInteger("Tce") == null ? null : Duration.ofSeconds(sockMessage.getInteger("Tce")),
                sockMessage.getString("Ext"),
                sockMessage.getString("IP"),
                AgentAccumulatorAdapter.adapt(sockMessage.getString("ata")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("isa")),
                AgentInternalAccumulatorAdapter.adapt(sockMessage.getString("iea")));
    }

}
