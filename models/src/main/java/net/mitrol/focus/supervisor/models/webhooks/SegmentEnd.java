package net.mitrol.focus.supervisor.models.webhooks;

import java.util.Date;

public class SegmentEnd {

    private String interactionId;
    private Integer segmentId;
    private Integer dialerPending;
    private Integer dialerFetching;
    private Integer dialerDialing;
    private Integer dialerRinging;
    private Boolean liveVoiceDetected;
    private Integer siteId;
    private Integer serverId;
    private Integer channelId;
    private Integer ivrSiteId;
    private Integer ivrServerId;
    private Integer ivrChannelId;
    private Date timestamp;
    private Integer direction;
    private Integer campaignId;
    private String customerAgentLoginId;
    private String agentLoginId;
    private Integer answered;
    private Integer preview;
    private Integer agentDialing;
    private Integer ringing;
    private Integer talking;
    private Integer hold;
    private Integer afterCallWork;
    private Integer inQueue;
    private Integer transferred;
    private Integer queuedTotal;
    private Integer resolutionId;
    private String originatingInteractionId;
    private Boolean queued;
    private Boolean firstTransferToAgent;
    private Boolean inbound;
    private Boolean transferredToAgent;
    private Boolean dropped;
    private Boolean flowIn;
    private Boolean flowOut;
    private Boolean agentAnswered;
    private Boolean agentDropped;
    private Boolean transferIn;
    private Boolean transferOut;
    private Boolean reply;
    private Boolean forward;
    private Integer contactType;
    private Integer dialerRecordId;
    private Integer dialerRecordContactId;
    private Integer messageId;
    private Integer caseId;
    private String crmId;
    private String remoteContact;
    private String dnis;
    private String ani;
    private Integer q850Casue;
    private Integer terminationSide;
    private Integer duration;
    private Integer billingDuration;
    private String context;

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getDialerPending() {
        return dialerPending;
    }

    public void setDialerPending(Integer dialerPending) {
        this.dialerPending = dialerPending;
    }

    public Integer getDialerFetching() {
        return dialerFetching;
    }

    public void setDialerFetching(Integer dialerFetching) {
        this.dialerFetching = dialerFetching;
    }

    public Integer getDialerDialing() {
        return dialerDialing;
    }

    public void setDialerDialing(Integer dialerDialing) {
        this.dialerDialing = dialerDialing;
    }

    public Integer getDialerRinging() {
        return dialerRinging;
    }

    public void setDialerRinging(Integer dialerRinging) {
        this.dialerRinging = dialerRinging;
    }

    public Boolean getLiveVoiceDetected() {
        return liveVoiceDetected;
    }

    public void setLiveVoiceDetected(Boolean liveVoiceDetected) {
        this.liveVoiceDetected = liveVoiceDetected;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getIvrSiteId() {
        return ivrSiteId;
    }

    public void setIvrSiteId(Integer ivrSiteId) {
        this.ivrSiteId = ivrSiteId;
    }

    public Integer getIvrServerId() {
        return ivrServerId;
    }

    public void setIvrServerId(Integer ivrServerId) {
        this.ivrServerId = ivrServerId;
    }

    public Integer getIvrChannelId() {
        return ivrChannelId;
    }

    public void setIvrChannelId(Integer ivrChannelId) {
        this.ivrChannelId = ivrChannelId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getCustomerAgentLoginId() {
        return customerAgentLoginId;
    }

    public void setCustomerAgentLoginId(String customerAgentLoginId) {
        this.customerAgentLoginId = customerAgentLoginId;
    }

    public String getAgentLoginId() {
        return agentLoginId;
    }

    public void setAgentLoginId(String agentLoginId) {
        this.agentLoginId = agentLoginId;
    }

    public Integer getAnswered() {
        return answered;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public Integer getPreview() {
        return preview;
    }

    public void setPreview(Integer preview) {
        this.preview = preview;
    }

    public Integer getAgentDialing() {
        return agentDialing;
    }

    public void setAgentDialing(Integer agentDialing) {
        this.agentDialing = agentDialing;
    }

    public Integer getRinging() {
        return ringing;
    }

    public void setRinging(Integer ringing) {
        this.ringing = ringing;
    }

    public Integer getTalking() {
        return talking;
    }

    public void setTalking(Integer talking) {
        this.talking = talking;
    }

    public Integer getHold() {
        return hold;
    }

    public void setHold(Integer hold) {
        this.hold = hold;
    }

    public Integer getAfterCallWork() {
        return afterCallWork;
    }

    public void setAfterCallWork(Integer afterCallWork) {
        this.afterCallWork = afterCallWork;
    }

    public Integer getInQueue() {
        return inQueue;
    }

    public void setInQueue(Integer inQueue) {
        this.inQueue = inQueue;
    }

    public Integer getTransferred() {
        return transferred;
    }

    public void setTransferred(Integer transferred) {
        this.transferred = transferred;
    }

    public Integer getQueuedTotal() {
        return queuedTotal;
    }

    public void setQueuedTotal(Integer queuedTotal) {
        this.queuedTotal = queuedTotal;
    }

    public Integer getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(Integer resolutionId) {
        this.resolutionId = resolutionId;
    }

    public String getOriginatingInteractionId() {
        return originatingInteractionId;
    }

    public void setOriginatingInteractionId(String originatingInteractionId) {
        this.originatingInteractionId = originatingInteractionId;
    }

    public Boolean getQueued() {
        return queued;
    }

    public void setQueued(Boolean queued) {
        this.queued = queued;
    }

    public Boolean getFirstTransferToAgent() {
        return firstTransferToAgent;
    }

    public void setFirstTransferToAgent(Boolean firstTransferToAgent) {
        this.firstTransferToAgent = firstTransferToAgent;
    }

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public Boolean getTransferredToAgent() {
        return transferredToAgent;
    }

    public void setTransferredToAgent(Boolean transferredToAgent) {
        this.transferredToAgent = transferredToAgent;
    }

    public Boolean getDropped() {
        return dropped;
    }

    public void setDropped(Boolean dropped) {
        this.dropped = dropped;
    }

    public Boolean getFlowIn() {
        return flowIn;
    }

    public void setFlowIn(Boolean flowIn) {
        this.flowIn = flowIn;
    }

    public Boolean getFlowOut() {
        return flowOut;
    }

    public void setFlowOut(Boolean flowOut) {
        this.flowOut = flowOut;
    }

    public Boolean getAgentAnswered() {
        return agentAnswered;
    }

    public void setAgentAnswered(Boolean agentAnswered) {
        this.agentAnswered = agentAnswered;
    }

    public Boolean getAgentDropped() {
        return agentDropped;
    }

    public void setAgentDropped(Boolean agentDropped) {
        this.agentDropped = agentDropped;
    }

    public Boolean getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(Boolean transferIn) {
        this.transferIn = transferIn;
    }

    public Boolean getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(Boolean transferOut) {
        this.transferOut = transferOut;
    }

    public Boolean getReply() {
        return reply;
    }

    public void setReply(Boolean reply) {
        this.reply = reply;
    }

    public Boolean getForward() {
        return forward;
    }

    public void setForward(Boolean forward) {
        this.forward = forward;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public Integer getDialerRecordId() {
        return dialerRecordId;
    }

    public void setDialerRecordId(Integer dialerRecordId) {
        this.dialerRecordId = dialerRecordId;
    }

    public Integer getDialerRecordContactId() {
        return dialerRecordContactId;
    }

    public void setDialerRecordContactId(Integer dialerRecordContactId) {
        this.dialerRecordContactId = dialerRecordContactId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getRemoteContact() {
        return remoteContact;
    }

    public void setRemoteContact(String remoteContact) {
        this.remoteContact = remoteContact;
    }

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public Integer getQ850Casue() {
        return q850Casue;
    }

    public void setQ850Casue(Integer q850Casue) {
        this.q850Casue = q850Casue;
    }

    public Integer getTerminationSide() {
        return terminationSide;
    }

    public void setTerminationSide(Integer terminationSide) {
        this.terminationSide = terminationSide;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBillingDuration() {
        return billingDuration;
    }

    public void setBillingDuration(Integer billingDuration) {
        this.billingDuration = billingDuration;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
