package net.mitrol.focus.supervisor.common.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author ladassus
 */
public class EventFilter {

    private List<String> campaignIds;
    private List<String> groupIds;
    private List<String> splitIds;
    private List<String> companyIds;
    private List<String> agentIds;
    private List<String> alertIds;
    private String dateFrom;
    private String dateTo;

    public List<String> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<String> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<String> getSplitIds() {
        return splitIds;
    }

    public void setSplitIds(List<String> splitIds) {
        this.splitIds = splitIds;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }

    public List<String> getAlertIds() {
        return alertIds;
    }

    public void setAlertIds(List<String> alertIds) {
        this.alertIds = alertIds;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
