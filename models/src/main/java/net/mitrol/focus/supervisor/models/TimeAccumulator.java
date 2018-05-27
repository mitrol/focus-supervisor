package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marce on 5/18/18.
 */
public class TimeAccumulator {

    private Map<AgentState, Duration> agentStateDurations;
    private Map<InteractionState, Duration> interactionStateDuration;
    private Duration login;
    private Duration talking;
    private Map<ResolutionCategory, Integer> resolutions;

    private TimeAccumulator(
            Duration agentUnstaffed, Duration agnetAvail, Duration agentPreview, Duration agentDial,
            Duration agentRing, Duration agentConnect, Duration agentHold, Duration agentAfterCallWork, Duration agentNotReady,
            Duration agentBreak0, Duration agentBreak1, Duration agentBreak2, Duration agentBreak3, Duration agentBreak4,
            Duration agentBreak5, Duration agentBreak6, Duration agentBreak7, Duration agentBreak8, Duration agentBreak9,
            Duration interactionPending, Duration interactionDialerDialing, Duration interactionAnswered, Duration interactionPreview,
            Duration interactionDialerAgent, Duration interactionRinging, Duration interactionTalking, Duration interactionHold,
            Duration interactionAfterCallWork, Duration interactionQueued, Duration interactionTransferred,
            Duration login, Duration talking,
            Integer otherResolutions, Integer successfulResolutions, Integer unsuccessfulResolutions, Integer ineffectiveResolutions, Integer neutralResolutions) {

        agentStateDurations = new HashMap<>();
        agentStateDurations.put(AgentState.UNSTAFFED, agentUnstaffed);
        agentStateDurations.put(AgentState.AVAIL, agnetAvail);
        agentStateDurations.put(AgentState.PREVIEW, agentPreview);
        agentStateDurations.put(AgentState.DIAL, agentDial);
        agentStateDurations.put(AgentState.RING, agentRing);
        agentStateDurations.put(AgentState.CONNECT, agentConnect);
        agentStateDurations.put(AgentState.HOLD, agentHold);
        agentStateDurations.put(AgentState.AFTER_CALL_WORK, agentAfterCallWork);
        agentStateDurations.put(AgentState.NOT_READY, agentNotReady);
        agentStateDurations.put(AgentState.BREAK_0, agentBreak0);
        agentStateDurations.put(AgentState.BREAK_1, agentBreak1);
        agentStateDurations.put(AgentState.BREAK_2, agentBreak2);
        agentStateDurations.put(AgentState.BREAK_3, agentBreak3);
        agentStateDurations.put(AgentState.BREAK_4, agentBreak4);
        agentStateDurations.put(AgentState.BREAK_5, agentBreak5);
        agentStateDurations.put(AgentState.BREAK_6, agentBreak6);
        agentStateDurations.put(AgentState.BREAK_7, agentBreak7);
        agentStateDurations.put(AgentState.BREAK_8, agentBreak8);
        agentStateDurations.put(AgentState.BREAK_9, agentBreak9);
        agentStateDurations = Collections.unmodifiableMap(agentStateDurations);

        interactionStateDuration = new HashMap<>();
        interactionStateDuration.put(InteractionState.PENDING, interactionPending);
        interactionStateDuration.put(InteractionState.DIALING_DIALER, interactionDialerDialing);
        interactionStateDuration.put(InteractionState.ANSWERED, interactionAnswered);
        interactionStateDuration.put(InteractionState.PREVIEW, interactionPreview);
        interactionStateDuration.put(InteractionState.DIALING_AGENT, interactionDialerAgent);
        interactionStateDuration.put(InteractionState.RINGING, interactionRinging);
        interactionStateDuration.put(InteractionState.TALKING, interactionTalking);
        interactionStateDuration.put(InteractionState.HOLD, interactionHold);
        interactionStateDuration.put(InteractionState.AFTER_CALL_WORK, interactionAfterCallWork);
        interactionStateDuration.put(InteractionState.QUEUED, interactionQueued);
        interactionStateDuration.put(InteractionState.TRANSFERRED, interactionTransferred);
        interactionStateDuration = Collections.unmodifiableMap(interactionStateDuration);

        this.login = login;
        this.talking = talking;

        resolutions = new HashMap<>();
        resolutions.put(ResolutionCategory.OTHER, otherResolutions);
        resolutions.put(ResolutionCategory.SUCCESSFUL, successfulResolutions);
        resolutions.put(ResolutionCategory.UNSUCCESSFUL, unsuccessfulResolutions);
        resolutions.put(ResolutionCategory.INEFFECTIVE, ineffectiveResolutions);
        resolutions.put(ResolutionCategory.NEUTRAL, neutralResolutions);
        resolutions = Collections.unmodifiableMap(resolutions);
    }

    public static TimeAccumulator parse(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new TimeAccumulator(
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
                values.length > 21 ? values[21] == null ? null : Duration.ofSeconds(values[21]) : null,
                values.length > 22 ? values[22] == null ? null : Duration.ofSeconds(values[22]) : null,
                values.length > 23 ? values[23] == null ? null : Duration.ofSeconds(values[23]) : null,
                values.length > 24 ? values[24] == null ? null : Duration.ofSeconds(values[24]) : null,
                values.length > 25 ? values[25] == null ? null : Duration.ofSeconds(values[25]) : null,
                values.length > 26 ? values[26] == null ? null : Duration.ofSeconds(values[26]) : null,
                values.length > 27 ? values[27] == null ? null : Duration.ofSeconds(values[27]) : null,
                values.length > 28 ? values[28] == null ? null : Duration.ofSeconds(values[28]) : null,
                values.length > 29 ? values[29] == null ? null : Duration.ofSeconds(values[29]) : null,
                values.length > 30 ? values[30] == null ? null : Duration.ofSeconds(values[30]) : null,
                values.length > 31 ? values[31] == null ? null : Duration.ofSeconds(values[31]) : null,
                values.length > 32 ? values[32] : null,
                values.length > 33 ? values[32] : null,
                values.length > 34 ? values[32] : null,
                values.length > 35 ? values[32] : null,
                values.length > 36 ? values[32] : null);
    }

    public Map<AgentState, Duration> getAgentStateDurations() {
        return agentStateDurations;
    }

    public Map<InteractionState, Duration> getInteractionStateDuration() {
        return interactionStateDuration;
    }

    public Duration getLogin() {
        return login;
    }

    public Duration getTalking() {
        return talking;
    }

    public Map<ResolutionCategory, Integer> getResolutions() {
        return resolutions;
    }
}