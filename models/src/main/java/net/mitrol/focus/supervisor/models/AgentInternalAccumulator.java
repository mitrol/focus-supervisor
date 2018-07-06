package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;

import java.time.Duration;

public class AgentInternalAccumulator {

    private Duration talking;
    private Integer abandoned;
    private Integer answered;
    private Integer incomingTransfer;
    private Integer outboundTransfer;

    private AgentInternalAccumulator(Duration talking, Integer abandoned, Integer answered, Integer incomingTransfer, Integer outboundTransfer) {
        this.talking = talking;
        this.abandoned = abandoned;
        this.answered = answered;
        this.incomingTransfer = incomingTransfer;
        this.outboundTransfer = outboundTransfer;
    }

    public AgentInternalAccumulator() {
    }

    public static AgentInternalAccumulator parse(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new AgentInternalAccumulator(
                values.length > 0 ? values[0] == null ? null : Duration.ofSeconds(values[0]) : null,
                values.length > 1 ? values[1] : null,
                values.length > 2 ? values[2] : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null);
    }

    public Duration getTalking() {
        return talking;
    }

    public Integer getAbandoned() {
        return abandoned;
    }

    public Integer getAnswered() {
        return answered;
    }

    public Integer getIncomingTransfer() {
        return incomingTransfer;
    }

    public Integer getOutboundTransfer() {
        return outboundTransfer;
    }
}