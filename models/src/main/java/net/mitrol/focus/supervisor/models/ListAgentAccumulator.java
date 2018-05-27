package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marce on 5/18/18.
 */
public class ListAgentAccumulator {

    private Duration login;
    private Duration talking;
    private Duration play;
    private Map<ResolutionCategory, Integer> resolutions;

    private ListAgentAccumulator(Duration login, Duration talking, Duration play, Integer otherResolutions, Integer successfulResolutions, Integer unsuccessfulResolutions, Integer ineffectiveResolutions, Integer neutralResolutions) {
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

    public static ListAgentAccumulator parse(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new ListAgentAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] == null ? null : Duration.ofSeconds(values[1]) : null,
                values.length > 2 ? values[2] == null ? null : Duration.ofSeconds(values[2]) : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null,
                values.length > 5 ? values[5] : null,
                values.length > 6 ? values[6] : null,
                values.length > 7 ? values[7] : null);
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
