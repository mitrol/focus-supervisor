package net.mitrol.focus.supervisor.models.webhooks;

import java.util.Date;

public class AgentLogin {

    private Date timestamp;
    private Integer agentId;
    private Integer groupId;
    private String extension;
    private String ip;
    private String version;

    public AgentLogin(Date timestamp, Integer agentId, Integer groupId, String extension, String ip, String version) {
        this.timestamp = timestamp;
        this.agentId = agentId;
        this.groupId = groupId;
        this.extension = extension;
        this.ip = ip;
        this.version = version;
    }

    public AgentLogin() {
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
