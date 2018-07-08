package net.mitrol.focus.supervisor.models;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AgentAccumulator {

    private Map<AgentState, Duration> agentStateDurations;
    private Duration login;
    private Duration talking;
    private Integer inboundDropped;
    private Integer inboundAnswered;
    private Integer outboundNotAnswered;
    private Integer outboundAnswered;
    private Integer dialerDropped;
    private Integer dialerAnswered;
    private Integer inboundTransfer;
    private Integer outboundTransfer;
    private Map<ResolutionCategory, Integer> resolutions;

    public AgentAccumulator() {
    }

    public AgentAccumulator(Duration unstaffed, Duration avail, Duration preview, Duration dial,
                             Duration ring, Duration connect, Duration hold, Duration afterCallWork, Duration notReady,
                             Duration break0, Duration break1, Duration break2, Duration break3, Duration break4,
                             Duration break5, Duration break6, Duration break7, Duration break8, Duration break9,
                             Duration login, Duration talking,
                             Integer inboundDropped, Integer inboundAnswered,
                             Integer outboundNotAnswered, Integer outboundAnswered,
                             Integer dialerDropped, Integer dialerAnswered,
                             Integer inboundTransfer, Integer outboundTransfer,
                             Integer otherResolutions, Integer successfulResolutions, Integer unsuccessfulResolutions, Integer ineffectiveResolutions, Integer neutralResolutions) {

        agentStateDurations = new HashMap<>();
        agentStateDurations.put(AgentState.UNSTAFFED, unstaffed);
        agentStateDurations.put(AgentState.AVAIL, avail);
        agentStateDurations.put(AgentState.PREVIEW, preview);
        agentStateDurations.put(AgentState.DIAL, dial);
        agentStateDurations.put(AgentState.RING, ring);
        agentStateDurations.put(AgentState.CONNECT, connect);
        agentStateDurations.put(AgentState.HOLD, hold);
        agentStateDurations.put(AgentState.AFTER_CALL_WORK, afterCallWork);
        agentStateDurations.put(AgentState.NOT_READY, notReady);
        agentStateDurations.put(AgentState.BREAK_0, break0);
        agentStateDurations.put(AgentState.BREAK_1, break1);
        agentStateDurations.put(AgentState.BREAK_2, break2);
        agentStateDurations.put(AgentState.BREAK_3, break3);
        agentStateDurations.put(AgentState.BREAK_4, break4);
        agentStateDurations.put(AgentState.BREAK_5, break5);
        agentStateDurations.put(AgentState.BREAK_6, break6);
        agentStateDurations.put(AgentState.BREAK_7, break7);
        agentStateDurations.put(AgentState.BREAK_8, break8);
        agentStateDurations.put(AgentState.BREAK_9, break9);
        agentStateDurations = Collections.unmodifiableMap(agentStateDurations);

        this.login = login;
        this.talking = talking;

        this.inboundDropped = inboundDropped;
        this.inboundAnswered = inboundAnswered;
        this.outboundNotAnswered = outboundNotAnswered;
        this.outboundAnswered = outboundAnswered;
        this.dialerDropped = dialerDropped;
        this.dialerAnswered = dialerAnswered;
        this.inboundTransfer = inboundTransfer;
        this.outboundTransfer = outboundTransfer;

        resolutions = new HashMap<>();
        resolutions.put(ResolutionCategory.OTHER, otherResolutions);
        resolutions.put(ResolutionCategory.SUCCESSFUL, successfulResolutions);
        resolutions.put(ResolutionCategory.UNSUCCESSFUL, unsuccessfulResolutions);
        resolutions.put(ResolutionCategory.INEFFECTIVE, ineffectiveResolutions);
        resolutions.put(ResolutionCategory.NEUTRAL, neutralResolutions);
        resolutions = Collections.unmodifiableMap(resolutions);
    }

    public Map<AgentState, Duration> getAgentStateDurations() {
        return agentStateDurations;
    }

    public Duration getLogin() {
        return login;
    }

    public Duration getTalking() {
        return talking;
    }

    public Integer getInboundDropped() {
        return inboundDropped;
    }

    public Integer getInboundAnswered() {
        return inboundAnswered;
    }

    public Integer getOutboundNotAnswered() {
        return outboundNotAnswered;
    }

    public Integer getOutboundAnswered() {
        return outboundAnswered;
    }

    public Integer getDialerDropped() {
        return dialerDropped;
    }

    public Integer getDialerAnswered() {
        return dialerAnswered;
    }

    public Integer getInboundTransfer() {
        return inboundTransfer;
    }

    public Integer getOutboundTransfer() {
        return outboundTransfer;
    }

    public Map<ResolutionCategory, Integer> getResolutions() {
        return resolutions;
    }
}