package net.mitrol.focus.supervisor.models.webhooks;

import java.util.Date;

public class AgentLogout {

    private Date loginTimestamp;
    private Date logoutTimestamp;
    private Integer duration;
    private Integer agentId;
    private Integer groupId;
    private String extension;
    private String ip;
    private String version;

    public AgentLogout(Date loginTimestamp, Date logoutTimestamp, Integer duration, Integer agentId, Integer groupId, String extension, String ip, String version) {
        this.loginTimestamp = loginTimestamp;
        this.logoutTimestamp = logoutTimestamp;
        this.duration = duration;
        this.agentId = agentId;
        this.groupId = groupId;
        this.extension = extension;
        this.ip = ip;
        this.version = version;
    }

    public AgentLogout() {
    }

    public Date getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(Date loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public Date getLogoutTimestamp() {
        return logoutTimestamp;
    }

    public void setLogoutTimestamp(Date logoutTimestamp) {
        this.logoutTimestamp = logoutTimestamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
