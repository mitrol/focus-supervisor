package net.mitrol.focus.supervisor.common.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ladassus
 */
public class EventDataValue {
    Object id;
    Object value;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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
