package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.AgentSplitAccumulator;

import java.time.Duration;

public class AgentSplitAccumulatorAdapter {
    public static AgentSplitAccumulator adapt(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new AgentSplitAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] == null ? null : Duration.ofSeconds(values[1]) : null,
                values.length > 2 ? values[2] == null ? null : Duration.ofSeconds(values[2]) : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null,
                values.length > 5 ? values[5] : null,
                values.length > 6 ? values[6] : null,
                values.length > 7 ? values[7] : null);
    }

}
