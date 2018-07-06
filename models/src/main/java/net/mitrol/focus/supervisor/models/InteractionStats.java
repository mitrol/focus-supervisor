package net.mitrol.focus.supervisor.models;

import net.mitrol.utils.entities.SockMessage;

import java.time.Duration;

public class InteractionStats {

    private Integer interactionStatsId;
    private Integer remoteAgentId;
    private Integer campaignId;
    private Integer listId;
    private Integer interactionType;
    private Integer contactType;
    private InteractionState state;
    private Duration duration;
    private String remoteParty;
    private String interactionId;
    private Integer segment;
    private Integer recordingCriterionId;
    private Boolean active;
    private Boolean activeInOrigin;
    private Duration queueTime;
    private Duration totalQueueTime;
    private Duration talking;
    private Boolean detectingSpeech;
    private Boolean detectingEmotion;
    private Boolean localWordSpotting;
    private Integer localGender;
    private Boolean remoteWordSpotting;
    private Integer remoteGender;

    public InteractionStats() {
    }

    private InteractionStats(Integer interactionStatsId, Integer remoteAgentId, Integer campaignId, Integer listId, Integer interactionType,
                             Integer contactType, InteractionState state, Duration duration, String remoteParty, String interactionId,
                             Integer segment, Integer recordingCriterionId, Boolean active, Boolean activeInOrigin, Duration queueTime,
                             Duration totalQueueTime, Duration talking, Boolean detectingSpeech, Boolean detectingEmotion, Boolean localWordSpotting,
                             Integer localGender, Boolean remoteWordSpotting, Integer remoteGender) {
        this.interactionStatsId = interactionStatsId;
        this.remoteAgentId = remoteAgentId;
        this.campaignId = campaignId;
        this.listId = listId;
        this.interactionType = interactionType;
        this.contactType = contactType;
        this.state = state;
        this.duration = duration;
        this.remoteParty = remoteParty;
        this.interactionId = interactionId;
        this.segment = segment;
        this.recordingCriterionId = recordingCriterionId;
        this.active = active;
        this.activeInOrigin = activeInOrigin;
        this.queueTime = queueTime;
        this.totalQueueTime = totalQueueTime;
        this.talking = talking;
        this.detectingSpeech = detectingSpeech;
        this.detectingEmotion = detectingEmotion;
        this.localWordSpotting = localWordSpotting;
        this.localGender = localGender;
        this.remoteWordSpotting = remoteWordSpotting;
        this.remoteGender = remoteGender;
    }

    public static InteractionStats parse(SockMessage sockMessage) {
        return new InteractionStats(
                sockMessage.getInteger("ida"),
                sockMessage.getInteger("idd"),
                sockMessage.getInteger("idc"),
                sockMessage.getInteger("idl"),
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

    public Integer getInteractionStatsId() {
        return interactionStatsId;
    }

    public Integer getRemoteAgentId() {
        return remoteAgentId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public Integer getListId() {
        return listId;
    }

    public Integer getInteractionType() {
        return interactionType;
    }

    public Integer getContactType() {
        return contactType;
    }

    public InteractionState getState() {
        return state;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getRemoteParty() {
        return remoteParty;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public Integer getSegment() {
        return segment;
    }

    public Integer getRecordingCriterionId() {
        return recordingCriterionId;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isActiveInOrigin() {
        return activeInOrigin;
    }

    public Duration getQueueTime() {
        return queueTime;
    }

    public Duration getTotalQueueTime() {
        return totalQueueTime;
    }

    public Duration getTalking() {
        return talking;
    }

    public Boolean isDetectingSpeech() {
        return detectingSpeech;
    }

    public Boolean isDetectingEmotion() {
        return detectingEmotion;
    }

    public Boolean isLocalWordSpotting() {
        return localWordSpotting;
    }

    public Integer getLocalGender() {
        return localGender;
    }

    public Boolean isRemoteWordSpotting() {
        return remoteWordSpotting;
    }

    public Integer getRemoteGender() {
        return remoteGender;
    }
}