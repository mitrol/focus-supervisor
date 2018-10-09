package net.mitrol.focus.supervisor.common.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ladassus
 */
public class EventResponse {

    private EventValues widgetValues;

    public EventValues getWidgetValues() {
        return widgetValues;
    }

    public void setWidgetValues(EventValues widgetValues) {
        this.widgetValues = widgetValues;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
