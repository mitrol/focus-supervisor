package net.mitrol.mitct.mitacd.event;

import net.mitrol.ct.api.enums.Channel;
import net.mitrol.ct.api.enums.InteractionState;

import java.util.Date;

public class InteractionEvent {

    public static final String TYPE = "interaction_event";

    private Date timestamp;
    private String interactionId;
    private Integer segment;
    private InteractionState state;
    private Integer campaignId;
    private Integer userId;
    private Integer groupId;
    private Integer recordId;
    private Integer recordContactId;
    private Integer messageId;
    private Integer messagingAccountId;
    private Channel channel; // tipo contacto
    private Integer resolutionId;
    private Integer resolutionCategoryId;

    public InteractionEvent() {
    }

    public InteractionEvent(Date timestamp, String interactionId, Integer segment, InteractionState state, Integer campaignId, Integer userId, Integer groupId, Integer recordId, Integer recordContactId, Integer messageId, Integer messagingAccountId, Channel channel, Integer resolutionId, Integer resolutionCategoryId) {
        this.timestamp = timestamp;
        this.interactionId = interactionId;
        this.segment = segment;
        this.state = state;
        this.campaignId = campaignId;
        this.userId = userId;
        this.groupId = groupId;
        this.recordId = recordId;
        this.recordContactId = recordContactId;
        this.messageId = messageId;
        this.messagingAccountId = messagingAccountId;
        this.channel = channel;
        this.resolutionId = resolutionId;
        this.resolutionCategoryId = resolutionCategoryId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRecordContactId() {
        return recordContactId;
    }

    public void setRecordContactId(Integer recordContactId) {
        this.recordContactId = recordContactId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessagingAccountId() {
        return messagingAccountId;
    }

    public void setMessagingAccountId(Integer messagingAccountId) {
        this.messagingAccountId = messagingAccountId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Integer getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(Integer resolutionId) {
        this.resolutionId = resolutionId;
    }

    public Integer getResolutionCategoryId() {
        return resolutionCategoryId;
    }

    public void setResolutionCategoryId(Integer resolutionCategoryId) {
        this.resolutionCategoryId = resolutionCategoryId;
    }

    public InteractionState getState() {
        return state;
    }

    public void setState(InteractionState state) {
        this.state = state;
    }
}
