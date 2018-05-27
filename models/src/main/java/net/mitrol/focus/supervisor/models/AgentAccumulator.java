package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;

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

    private AgentAccumulator(Duration unstaffed, Duration avail, Duration preview, Duration dial,
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

    public static AgentAccumulator parse(String s) {

        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new AgentAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] == null ? null : Duration.ofSeconds(values[1]) : null,
                values.length > 2 ? values[2] == null ? null : Duration.ofSeconds(values[2]) : null,
                values.length > 3 ? values[3] == null ? null : Duration.ofSeconds(values[3]) : null,
                values.length > 4 ? values[4] == null ? null : Duration.ofSeconds(values[4]) : null,
                values.length > 5 ? values[5] == null ? null : Duration.ofSeconds(values[5]) : null,
                values.length > 6 ? values[6] == null ? null : Duration.ofSeconds(values[6]) : null,
                values.length > 7 ? values[7] == null ? null : Duration.ofSeconds(values[7]) : null,
                values.length > 8 ? values[8] == null ? null : Duration.ofSeconds(values[8]) : null,
                values.length > 9 ? values[9] == null ? null : Duration.ofSeconds(values[9]) : null,
                values.length > 10 ? values[10] == null ? null : Duration.ofSeconds(values[10]) : null,
                values.length > 11 ? values[11] == null ? null : Duration.ofSeconds(values[11]) : null,
                values.length > 12 ? values[12] == null ? null : Duration.ofSeconds(values[12]) : null,
                values.length > 13 ? values[13] == null ? null : Duration.ofSeconds(values[13]) : null,
                values.length > 14 ? values[14] == null ? null : Duration.ofSeconds(values[14]) : null,
                values.length > 15 ? values[15] == null ? null : Duration.ofSeconds(values[15]) : null,
                values.length > 16 ? values[16] == null ? null : Duration.ofSeconds(values[16]) : null,
                values.length > 17 ? values[17] == null ? null : Duration.ofSeconds(values[17]) : null,
                values.length > 18 ? values[18] == null ? null : Duration.ofSeconds(values[18]) : null,
                values.length > 19 ? values[19] == null ? null : Duration.ofSeconds(values[19]) : null,
                values.length > 20 ? values[20] == null ? null : Duration.ofSeconds(values[20]) : null,
                values.length > 21 ? values[21] : null,
                values.length > 22 ? values[22] : null,
                values.length > 23 ? values[23] : null,
                values.length > 24 ? values[24] : null,
                values.length > 25 ? values[25] : null,
                values.length > 26 ? values[26] : null,
                values.length > 27 ? values[27] : null,
                values.length > 28 ? values[28] : null,
                values.length > 29 ? values[29] : null,
                values.length > 30 ? values[30] : null,
                values.length > 31 ? values[31] : null,
                values.length > 32 ? values[32] : null,
                values.length > 33 ? values[33] : null
        );
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