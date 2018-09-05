package net.mitrol.focus.supervisor.common.util;

import java.util.List;

/**
 * @author ladassus
 */
public class EventMessageFilter {

    private List<Long> campaignId;
    private Long groupId;
    private List<Long> splitId;
    private List<Long> companyId;
    private String dateFrom;
    private String dateTo;

    public List<Long> getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(List<Long> campaignId) {
        this.campaignId = campaignId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getSplitId() {
        return splitId;
    }

    public void setSplitId(List<Long> splitId) {
        this.splitId = splitId;
    }

    public List<Long> getCompanyId() {
        return companyId;
    }

    public void setCompanyId(List<Long> companyId) {
        this.companyId = companyId;
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
}
