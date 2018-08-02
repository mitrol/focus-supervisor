package net.mitrol.focus.supervisor.mitacd.connector.adapter;

import net.mitrol.ct.api.enums.InteractionState;
import net.mitrol.focus.supervisor.mitacd.connector.util.MitAcdUtils;
import net.mitrol.focus.supervisor.models.InteractionStats;
import net.mitrol.utils.entities.SockMessage;

import java.time.Duration;

public class InteractionStatsAdapter {
    public static InteractionStats adapt(SockMessage sockMessage) {
        return new InteractionStats(
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("ida")),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idd")),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idg")),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idc")),
                MitAcdUtils.getIntNullZero(sockMessage.getInteger("idl")),
                sockMessage.getInteger("t"),
                sockMessage.getInteger("tct"),
                InteractionState.getFromCode(sockMessage.getInteger("st")),
                sockMessage.getInteger("Te") == null ? null : Duration.ofSeconds(sockMessage.getInteger("Te")),
                sockMessage.getString("C"),
                sockMessage.getString("idLl"),
                sockMessage.getInteger("s"),
                sockMessage.getInteger("rec"),
                sockMessage.getBoolean("aa"),
                sockMessage.getBoolean("ad"),
                sockMessage.getInteger("TC") == null ? null : Duration.ofSeconds(sockMessage.getInteger("TC")),
                sockMessage.getInteger("TCA") == null ? null : Duration.ofSeconds(sockMessage.getInteger("TCA")),
                sockMessage.getInteger("Th") == null ? null : Duration.ofSeconds(sockMessage.getInteger("Th")),
                sockMessage.getBoolean("spro"),
                sockMessage.getBoolean("emo"),
                sockMessage.getBoolean("wso"),
                sockMessage.getInteger("go"),
                sockMessage.getBoolean("wsc"),
                sockMessage.getInteger("gc"));
    }

}
