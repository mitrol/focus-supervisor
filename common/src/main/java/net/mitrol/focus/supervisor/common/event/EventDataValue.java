package net.mitrol.focus.supervisor.common.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ladassus
 */
public class EventDataValue {
    String id;
    Object value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
