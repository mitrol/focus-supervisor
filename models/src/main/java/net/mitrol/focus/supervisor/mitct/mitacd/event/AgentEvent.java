package net.mitrol.focus.supervisor.mitct.mitacd.event;

import java.util.Date;

/**
 * Sucede cuando un agente cambia de estado
 */
public class AgentEvent {

    public static final String TYPE = "agent_event";

    private Date timestamp;
    private int userId;
    private int groupId;
    private AgentState state;

    public AgentEvent() {
    }

    public AgentEvent(Date timestamp, int userId, int groupId, AgentState state) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.groupId = groupId;
        this.state = state;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public AgentState getState() {
        return state;
    }

    public void setState(AgentState state) {
        this.state = state;
    }
}
