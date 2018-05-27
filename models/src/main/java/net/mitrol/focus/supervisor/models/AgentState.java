package net.mitrol.focus.supervisor.models;

import java.util.Arrays;

public enum AgentState {

    UNSTAFFED(0),
    AVAIL(1),
    PREVIEW(2),
    DIAL(3),
    RING(4),
    CONNECT(5),
    HOLD(6),
    AFTER_CALL_WORK(7),
    NOT_READY(8),
    BREAK_0(9),
    BREAK_1(10),
    BREAK_2(11),
    BREAK_3(12),
    BREAK_4(13),
    BREAK_5(14),
    BREAK_6(15),
    BREAK_7(16),
    BREAK_8(17),
    BREAK_9(18),
    UNSPECIFIED_BREAK(-2),
    EMPTY(-1);

    private final int code;

    AgentState(int code) {
        this.code = code;
    }

    public static AgentState getFromCode(int code) {
        return Arrays.stream(values()).filter(x -> x.code == code).findFirst().orElse(null);
    }

    public int getCode() {
        return code;
    }
}