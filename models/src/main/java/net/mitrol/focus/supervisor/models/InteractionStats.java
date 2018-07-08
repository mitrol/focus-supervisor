package net.mitrol.focus.supervisor.models;

import java.time.Duration;

public class InteractionStats {

    private Integer agentId;
    private Integer destinationAgentId;
    private Integer groupId;
    private Integer campaignId;
    private Integer splitId;
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

    public InteractionStats(Integer agentId, Integer destinationAgentId, Integer groupId, Integer campaignId, Integer splitId, Integer interactionType, Integer contactType, InteractionState state, Duration duration, String remoteParty, String interactionId, Integer segment, Integer recordingCriterionId, Boolean active, Boolean activeInOrigin, Duration queueTime, Duration totalQueueTime, Duration talking, Boolean detectingSpeech, Boolean detectingEmotion, Boolean localWordSpotting, Integer localGender, Boolean remoteWordSpotting, Integer remoteGender) {
        this.agentId = agentId;
        this.destinationAgentId = destinationAgentId;
        this.groupId = groupId;
        this.campaignId = campaignId;
        this.splitId = splitId;
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

    public Integer getAgentId() {
        return agentId;
    }

    public Integer getDestinationAgentId() {
        return destinationAgentId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public Integer getSplitId() {
        return splitId;
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