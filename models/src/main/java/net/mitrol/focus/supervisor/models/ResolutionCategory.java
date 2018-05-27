package net.mitrol.focus.supervisor.models;

import java.util.Arrays;

public enum ResolutionCategory {

    OTHER(0),
    SUCCESSFUL(1),
    UNSUCCESSFUL(2),
    INEFFECTIVE(3),
    NEUTRAL(4),
    EMPTY(-1);

    private final int code;

    ResolutionCategory(int code) {
        this.code = code;
    }

    public static ResolutionCategory getFromCode(int code) {
        return Arrays.stream(values()).filter(x -> x.code == code).findFirst().orElse(null);
    }
}