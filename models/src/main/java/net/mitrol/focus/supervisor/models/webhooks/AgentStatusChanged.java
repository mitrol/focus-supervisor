package net.mitrol.focus.supervisor.models.webhooks;

public class AgentStatusChanged {

    private String timestamp;
    private Integer agentId;
    private Integer order;
    private Integer previousStateId;
    private Integer stateId;
    private Integer duration;
    private Integer interactions;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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

    public Integer getPreviousStateId() {
        return previousStateId;
    }

    public void setPreviousStateId(Integer previousStateId) {
        this.previousStateId = previousStateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
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
}
