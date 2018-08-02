package net.mitrol.focus.supervisor.models;

import net.mitrol.ct.api.enums.AgentState;

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
        agentStateDurations.put(AgentState.Unstaffed, unstaffed);
        agentStateDurations.put(AgentState.Avail, avail);
        agentStateDurations.put(AgentState.Preview, preview);
        agentStateDurations.put(AgentState.Dial, dial);
        agentStateDurations.put(AgentState.Ring, ring);
        agentStateDurations.put(AgentState.Connect, connect);
        agentStateDurations.put(AgentState.Hold, hold);
        agentStateDurations.put(AgentState.AfterCallWork, afterCallWork);
        agentStateDurations.put(AgentState.NotReady, notReady);
        agentStateDurations.put(AgentState.Break0, break0);
        agentStateDurations.put(AgentState.Break1, break1);
        agentStateDurations.put(AgentState.Break2, break2);
        agentStateDurations.put(AgentState.Break3, break3);
        agentStateDurations.put(AgentState.Break4, break4);
        agentStateDurations.put(AgentState.Break5, break5);
        agentStateDurations.put(AgentState.Break6, break6);
        agentStateDurations.put(AgentState.Break7, break7);
        agentStateDurations.put(AgentState.Break8, break8);
        agentStateDurations.put(AgentState.Break9, break9);
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