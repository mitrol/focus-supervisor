package net.mitrol.focus.supervisor.common.event;

/**
 * @author ladassus
 */
public class EventData {
    String name;
    Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
