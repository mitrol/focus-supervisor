package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

import java.time.Duration;

public class CampaignIntervalStats {

    private Integer campaignIntervalStatsId;
    private TimeAccumulator times;
    private InteractionAccumulator interactions;
    private Integer queued;
    private Duration waitingTime;
    private Integer serviceLevel;
    private Duration averageSpeedOfAnswer;
    private Duration impatience;
    private Integer liveVoiceProbability;
    private Duration estimatedWaitingTime;
    private Duration averageHandlingTime;
    private Duration averageTalkingTime;

    public CampaignIntervalStats() {
    }

    private CampaignIntervalStats(Integer campaignIntervalStatsId, TimeAccumulator times, InteractionAccumulator interactions, Integer queued, Duration waitingTime, Integer serviceLevel, Duration averageSpeedOfAnswer, Duration impatience, Integer liveVoiceProbability, Duration estimatedWaitingTime, Duration averageHandlingTime, Duration averageTalkingTime) {
        this.campaignIntervalStatsId = campaignIntervalStatsId;
        this.times = times;
        this.interactions = interactions;
        this.queued = queued;
        this.waitingTime = waitingTime;
        this.serviceLevel = serviceLevel;
        this.averageSpeedOfAnswer = averageSpeedOfAnswer;
        this.impatience = impatience;
        this.liveVoiceProbability = liveVoiceProbability;
        this.estimatedWaitingTime = estimatedWaitingTime;
        this.averageHandlingTime = averageHandlingTime;
        this.averageTalkingTime = averageTalkingTime;
    }

    public static CampaignIntervalStats parse(SockMessage sockMessage) {
        return new CampaignIntervalStats(
                sockMessage.getInteger("id"),
                TimeAccumulator.parse(sockMessage.getString("ata")),
                InteractionAccumulator.parse(sockMessage.getString("ala")),
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

    public Integer getCampaignIntervalStatsId() {
        return campaignIntervalStatsId;
    }

    public TimeAccumulator getTimes() {
        return times;
    }

    public InteractionAccumulator getInteractions() {
        return interactions;
    }

    public Integer getQueued() {
        return queued;
    }

    public Duration getWaitingTime() {
        return waitingTime;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public Duration getAverageSpeedOfAnswer() {
        return averageSpeedOfAnswer;
    }

    public Duration getImpatience() {
        return impatience;
    }

    public Integer getLiveVoiceProbability() {
        return liveVoiceProbability;
    }

    public Duration getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public Duration getAverageHandlingTime() {
        return averageHandlingTime;
    }

    public Duration getAverageTalkingTime() {
        return averageTalkingTime;
    }
}