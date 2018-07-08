package net.mitrol.focus.supervisor.models;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marce on 5/18/18.
 */
public class CampaignTimeAccumulator {

    private Map<AgentState, Duration> agentStateDurations;
    private Map<InteractionState, Duration> interactionStateDuration;
    private Duration login;
    private Duration talking;
    private Map<ResolutionCategory, Integer> resolutions;

    public CampaignTimeAccumulator() {
    }

    public CampaignTimeAccumulator(
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