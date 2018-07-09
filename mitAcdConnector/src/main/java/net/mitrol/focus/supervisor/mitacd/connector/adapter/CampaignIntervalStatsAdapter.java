package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.focus.supervisor.models.CampaignIntervalStats;
import net.mitrol.utils.entities.SockMessage;

import java.time.Duration;

public class CampaignIntervalStatsAdapter {
    public static CampaignIntervalStats adapt(SockMessage sockMessage) {
        return new CampaignIntervalStats(
                sockMessage.getInteger("id"),
                CampaignTimeAccumulatorAdapter.adapt(sockMessage.getString("ata")),
                InteractionAccumulatorAdapter.adapt(sockMessage.getString("ala")),
                sockMessage.getInteger("L"),
                sockMessage.getInteger("WT") == null ? null : Duration.ofSeconds(sockMessage.getInteger("WT")),
                sockMessage.getInteger("FS"),
                sockMessage.getInteger("ASA") == null ? null : Duration.ofSeconds(sockMessage.getInteger("ASA")),
                sockMessage.getInteger("Imp") == null ? null : Duration.ofSeconds(sockMessage.getInteger("Imp")),
                sockMessage.getInteger("Pvv"),
                sockMessage.getInteger("ewt") == null ? null : Duration.ofSeconds(sockMessage.getInteger("ewt")),
                sockMessage.getInteger("AHT") == null ? null : Duration.ofSeconds(sockMessage.getInteger("AHT")),
                sockMessage.getInteger("ATT") == null ? null : Duration.ofSeconds(sockMessage.getInteger("ATT")));
    }
}
