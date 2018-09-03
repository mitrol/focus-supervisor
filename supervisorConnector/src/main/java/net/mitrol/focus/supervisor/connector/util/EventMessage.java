package net.mitrol.focus.supervisor.connector.util;

public class MessageEvent {

    private Long id;
    private String eventType;
    private Long refreshInterval;
    private String widgetType;
    private Long agentId;
    private MessageEventFilter filter;

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

    public MessageEventFilter getFilter() {
        return filter;
    }

    public void setFilter(MessageEventFilter filter) {
        this.filter = filter;
    }
}
