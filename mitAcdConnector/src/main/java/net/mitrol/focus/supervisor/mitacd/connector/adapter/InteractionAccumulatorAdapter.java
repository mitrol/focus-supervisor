package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.InteractionAccumulator;

public class InteractionAccumulatorAdapter {
    public static InteractionAccumulator adapt(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new InteractionAccumulator(
                values.length > 0 ? values[0] : null,
                values.length > 1 ? values[1] : null,
                values.length > 2 ? values[2] : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null,
                values.length > 5 ? values[5] : null,
                values.length > 6 ? values[6] : null,
                values.length > 7 ? values[7] : null,
                values.length > 8 ? values[8] : null,
                values.length > 9 ? values[9] : null,
                values.length > 10 ? values[10] : null,
                values.length > 11 ? values[11] : null,
                values.length > 12 ? values[12] : null,
                values.length > 13 ? values[13] : null,
                values.length > 14 ? values[14] : null,
                values.length > 15 ? values[15] : null,
                values.length > 16 ? values[16] : null,
                values.length > 17 ? values[17] : null,
                values.length > 18 ? values[18] : null,
                values.length > 19 ? values[19] : null,
                values.length > 20 ? values[20] : null,
                values.length > 21 ? values[21] : null);
    }
}
