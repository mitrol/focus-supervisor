package net.mitrol.focus.supervisor.connector.dto.widgets;

import java.util.List;

public class WidgetDTO {

    private Integer widgetId;
    private List<WidgetValueDTO> values;

    public Integer getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Integer widgetId) {
        this.widgetId = widgetId;
    }

    public List<WidgetValueDTO> getValues() {
        return values;
    }

    public void setValues(List<WidgetValueDTO> values) {
        this.values = values;
    }
}
