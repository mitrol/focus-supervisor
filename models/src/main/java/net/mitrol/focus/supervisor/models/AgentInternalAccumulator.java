package net.mitrol.focus.supervisor.models;

import java.time.Duration;

public class AgentInternalAccumulator {

    private Duration talking;
    private Integer abandoned;
    private Integer answered;
    private Integer incomingTransfer;
    private Integer outboundTransfer;

    public AgentInternalAccumulator() {
    }

    public AgentInternalAccumulator(Duration talking, Integer abandoned, Integer answered, Integer incomingTransfer, Integer outboundTransfer) {
        this.talking = talking;
        this.abandoned = abandoned;
        this.answered = answered;
        this.incomingTransfer = incomingTransfer;
        this.outboundTransfer = outboundTransfer;
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