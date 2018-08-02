package net.mitrol.focus.supervisor.models.webhooks;

import net.mitrol.ct.api.enums.AgentState;

import java.util.Date;

public class AgentStatusChanged {

    private Date timestamp;
    private Integer agentId;
    private Integer order;
    private AgentState previousStateId;
    private AgentState stateId;
    private Integer duration;
    private Integer interactions;

    public AgentStatusChanged(Date timestamp, Integer agentId, Integer order, AgentState previousStateId, AgentState stateId, Integer duration, Integer interactions) {
        this.timestamp = timestamp;
        this.agentId = agentId;
        this.order = order;
        this.previousStateId = previousStateId;
        this.stateId = stateId;
        this.duration = duration;
        this.interactions = interactions;
    }

    public AgentStatusChanged() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getInteractions() {
        return interactions;
    }

    public void setInteractions(Integer interactions) {
        this.interactions = interactions;
    }

    public AgentState getPreviousStateId() {
        return previousStateId;
    }

    public void setPreviousStateId(AgentState previousStateId) {
        this.previousStateId = previousStateId;
    }

    public AgentState getStateId() {
        return stateId;
    }

    public void setStateId(AgentState stateId) {
        this.stateId = stateId;
    }
}
