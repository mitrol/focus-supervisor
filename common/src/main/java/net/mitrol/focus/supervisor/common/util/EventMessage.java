package net.mitrol.focus.supervisor.common.util;

/**
 * @author ladassus
 */
public class EventMessage {

    private Long id;
    private String eventType;
    private Long refreshInterval;
    private String widgetType;
    private Long agentId;
    private EventMessageFilter filter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Long refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public EventMessageFilter getFilter() {
        return filter;
    }

    public void setFilter(EventMessageFilter filter) {
        this.filter = filter;
    }
}
