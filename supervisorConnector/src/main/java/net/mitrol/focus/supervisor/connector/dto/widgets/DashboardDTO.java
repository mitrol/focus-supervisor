package net.mitrol.focus.supervisor.connector.dto.widgets;

import java.util.List;

public class DashboardDTO {

    private Integer dashboardId;
    private List<WidgetDTO> updatedWidgets;

    public Integer getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(Integer dashboardId) {
        this.dashboardId = dashboardId;
    }

    public List<WidgetDTO> getUpdatedWidgets() {
        return updatedWidgets;
    }

    public void setUpdatedWidgets(List<WidgetDTO> updatedWidgets) {
        this.updatedWidgets = updatedWidgets;
    }
}
