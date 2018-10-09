package net.mitrol.focus.supervisor.common.enums;

/**
 * Event Types
 * @author ladassus
 */
public enum EventType {
    SUBSCRIBE("SUBSCRIBE"),
    UNSUBSCRIBE("UNSUBSCRIBE"),
    FILTERCHANGE("FILTERCHANGE");

    private String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
