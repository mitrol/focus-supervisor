package net.mitrol.focus.supervisor.common.event;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author ladassus
 */
public class EventFilter {

    private List<Long> campaignIds;
    private List<Long> groupId;
    private List<Long> splitIds;
    private List<Long> companyIds;
    private List<Long> agentIds;
    private String dateFrom;
    private String dateTo;

    public List<Long> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<Long> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public List<Long> getSplitIds() {
        return splitIds;
    }

    public void setSplitIds(List<Long> splitIds) {
        this.splitIds = splitIds;
    }

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
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

    public List<Long> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Long> groupId) {
        this.groupId = groupId;
    }

    public List<Long> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<Long> agentIds) {
        this.agentIds = agentIds;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
