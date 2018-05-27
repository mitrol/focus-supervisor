package net.mitrol.focus.supervisor.models;

import java.util.Arrays;

/**
 * Created by marce on 5/18/18.
 */
public enum InteractionState {

    IDLE(0),
    PENDING(1),
    DIALING_DIALER(2),
    ANSWERED(3),
    PREVIEW(4),
    DIALING_AGENT(5),
    RINGING(6),
    TALKING(7),
    HOLD(8),
    AFTER_CALL_WORK(9),
    QUEUED(10),
    TRANSFERRED(11),
    EMPTY(-1);

    private final int code;

    InteractionState(int code) {
        this.code = code;
    }

    public static InteractionState getFromCode(int code) {
        return Arrays.stream(values()).filter(x -> x.code == code).findFirst().orElse(null);
    }

    public int getCode() {
        return code;
    }
}