package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.CampaignTimeAccumulator;

import java.time.Duration;

public class CampaignTimeAccumulatorAdapter {
    public static CampaignTimeAccumulator adapt(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new CampaignTimeAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] == null ? null : Duration.ofSeconds(values[1]) : null,
                values.length > 2 ? values[2] == null ? null : Duration.ofSeconds(values[2]) : null,
                values.length > 3 ? values[3] == null ? null : Duration.ofSeconds(values[3]) : null,
                values.length > 4 ? values[4] == null ? null : Duration.ofSeconds(values[4]) : null,
                values.length > 5 ? values[5] == null ? null : Duration.ofSeconds(values[5]) : null,
                values.length > 6 ? values[6] == null ? null : Duration.ofSeconds(values[6]) : null,
                values.length > 7 ? values[7] == null ? null : Duration.ofSeconds(values[7]) : null,
                values.length > 8 ? values[8] == null ? null : Duration.ofSeconds(values[8]) : null,
                values.length > 9 ? values[9] == null ? null : Duration.ofSeconds(values[9]) : null,
                values.length > 10 ? values[10] == null ? null : Duration.ofSeconds(values[10]) : null,
                values.length > 11 ? values[11] == null ? null : Duration.ofSeconds(values[11]) : null,
                values.length > 12 ? values[12] == null ? null : Duration.ofSeconds(values[12]) : null,
                values.length > 13 ? values[13] == null ? null : Duration.ofSeconds(values[13]) : null,
                values.length > 14 ? values[14] == null ? null : Duration.ofSeconds(values[14]) : null,
                values.length > 15 ? values[15] == null ? null : Duration.ofSeconds(values[15]) : null,
                values.length > 16 ? values[16] == null ? null : Duration.ofSeconds(values[16]) : null,
                values.length > 17 ? values[17] == null ? null : Duration.ofSeconds(values[17]) : null,
                values.length > 18 ? values[18] == null ? null : Duration.ofSeconds(values[18]) : null,
                values.length > 19 ? values[19] == null ? null : Duration.ofSeconds(values[19]) : null,
                values.length > 20 ? values[20] == null ? null : Duration.ofSeconds(values[20]) : null,
                values.length > 21 ? values[21] == null ? null : Duration.ofSeconds(values[21]) : null,
                values.length > 22 ? values[22] == null ? null : Duration.ofSeconds(values[22]) : null,
                values.length > 23 ? values[23] == null ? null : Duration.ofSeconds(values[23]) : null,
                values.length > 24 ? values[24] == null ? null : Duration.ofSeconds(values[24]) : null,
                values.length > 25 ? values[25] == null ? null : Duration.ofSeconds(values[25]) : null,
                values.length > 26 ? values[26] == null ? null : Duration.ofSeconds(values[26]) : null,
                values.length > 27 ? values[27] == null ? null : Duration.ofSeconds(values[27]) : null,
                values.length > 28 ? values[28] == null ? null : Duration.ofSeconds(values[28]) : null,
                values.length > 29 ? values[29] == null ? null : Duration.ofSeconds(values[29]) : null,
                values.length > 30 ? values[30] == null ? null : Duration.ofSeconds(values[30]) : null,
                values.length > 31 ? values[31] == null ? null : Duration.ofSeconds(values[31]) : null,
                values.length > 32 ? values[32] : null,
                values.length > 33 ? values[32] : null,
                values.length > 34 ? values[32] : null,
                values.length > 35 ? values[32] : null,
                values.length > 36 ? values[32] : null);
    }
}
