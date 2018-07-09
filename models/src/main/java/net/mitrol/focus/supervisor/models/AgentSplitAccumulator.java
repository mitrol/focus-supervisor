package net.mitrol.focus.supervisor.models;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marce on 5/18/18.
 */
public class AgentSplitAccumulator {

    private Duration login;
    private Duration talking;
    private Duration play;
    private Map<ResolutionCategory, Integer> resolutions;

    public AgentSplitAccumulator() {
    }

    public AgentSplitAccumulator(Duration login, Duration talking, Duration play, Integer otherResolutions, Integer successfulResolutions, Integer unsuccessfulResolutions, Integer ineffectiveResolutions, Integer neutralResolutions) {
        this.login = login;
        this.talking = talking;
        this.play = play;
        resolutions = new HashMap<>();
        resolutions.put(ResolutionCategory.OTHER, otherResolutions);
        resolutions.put(ResolutionCategory.SUCCESSFUL, successfulResolutions);
        resolutions.put(ResolutionCategory.UNSUCCESSFUL, unsuccessfulResolutions);
        resolutions.put(ResolutionCategory.INEFFECTIVE, ineffectiveResolutions);
        resolutions.put(ResolutionCategory.NEUTRAL, neutralResolutions);
        resolutions = Collections.unmodifiableMap(resolutions);
    }

    public Duration getLogin() {
        return login;
    }

    public Duration getTalking() {
        return talking;
    }

    public Duration getPlay() {
        return play;
    }

    public Map<ResolutionCategory, Integer> getResolutions() {
        return resolutions;
    }
}
