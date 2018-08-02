package net.mitrol.focus.supervisor.models;

import net.mitrol.ct.api.enums.AgentState;
import net.mitrol.ct.api.enums.InteractionState;

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
        agentStateDurations.put(AgentState.Unstaffed, agentUnstaffed);
        agentStateDurations.put(AgentState.Avail, agnetAvail);
        agentStateDurations.put(AgentState.Preview, agentPreview);
        agentStateDurations.put(AgentState.Dial, agentDial);
        agentStateDurations.put(AgentState.Ring, agentRing);
        agentStateDurations.put(AgentState.Connect, agentConnect);
        agentStateDurations.put(AgentState.Hold, agentHold);
        agentStateDurations.put(AgentState.AfterCallWork, agentAfterCallWork);
        agentStateDurations.put(AgentState.NotReady, agentNotReady);
        agentStateDurations.put(AgentState.Break0, agentBreak0);
        agentStateDurations.put(AgentState.Break1, agentBreak1);
        agentStateDurations.put(AgentState.Break2, agentBreak2);
        agentStateDurations.put(AgentState.Break3, agentBreak3);
        agentStateDurations.put(AgentState.Break4, agentBreak4);
        agentStateDurations.put(AgentState.Break5, agentBreak5);
        agentStateDurations.put(AgentState.Break6, agentBreak6);
        agentStateDurations.put(AgentState.Break7, agentBreak7);
        agentStateDurations.put(AgentState.Break8, agentBreak8);
        agentStateDurations.put(AgentState.Break9, agentBreak9);
        agentStateDurations = Collections.unmodifiableMap(agentStateDurations);

        interactionStateDuration = new HashMap<>();
        interactionStateDuration.put(InteractionState.Pending, interactionPending);
        interactionStateDuration.put(InteractionState.DialingDiscador, interactionDialerDialing);
        interactionStateDuration.put(InteractionState.Answered, interactionAnswered);
        interactionStateDuration.put(InteractionState.Preview, interactionPreview);
        interactionStateDuration.put(InteractionState.DialingAgente, interactionDialerAgent);
        interactionStateDuration.put(InteractionState.Ringing, interactionRinging);
        interactionStateDuration.put(InteractionState.Talking, interactionTalking);
        interactionStateDuration.put(InteractionState.Hold, interactionHold);
        interactionStateDuration.put(InteractionState.AfterCallWork, interactionAfterCallWork);
        interactionStateDuration.put(InteractionState.EnCola, interactionQueued);
        interactionStateDuration.put(InteractionState.Transferida, interactionTransferred);
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