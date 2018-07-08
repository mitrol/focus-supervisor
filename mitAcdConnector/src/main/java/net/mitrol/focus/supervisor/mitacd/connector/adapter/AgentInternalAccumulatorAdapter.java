package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.AgentInternalAccumulator;

import java.time.Duration;

public class AgentInternalAccumulatorAdapter {
    public static AgentInternalAccumulator adapt(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new AgentInternalAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] : null,
                values.length > 2 ? values[2] : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null);
    }

}
