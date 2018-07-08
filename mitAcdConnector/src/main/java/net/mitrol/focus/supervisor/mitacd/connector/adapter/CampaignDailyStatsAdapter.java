package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.models.CampaignDailyStats;
import net.mitrol.utils.entities.SockMessage;

public class CampaignDailyStatsAdapter {
    public static CampaignDailyStats adapt(SockMessage sockMessage) {
        return new CampaignDailyStats(
                sockMessage.getInteger("id"),
                CampaignTimeAccumulatorAdapter.adapt(sockMessage.getString("atn")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("aln")),
                CampaignTimeAccumulatorAdapter.adapt(sockMessage.getString("atd")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("ald")));
    }
}
