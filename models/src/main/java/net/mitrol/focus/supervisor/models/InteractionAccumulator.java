package net.mitrol.focus.supervisor.models;

/**
 * Created by marce on 5/18/18.
 */
public class InteractionAccumulator {

    private Integer attempted; // Disparadas
    private Integer notExecuted; // NoEjecutadas
    private Integer notContacted; // NoContacto
    private Integer contacted; // Contactadas
    private Integer busy; // Ocupadas
    private Integer noAnswer; // NoContactoNoContesta
    private Integer notContactedByOtherReason; //NoContactoOtrosNoContacto
    private Integer fax; // NoContactoFAX
    private Integer answeringMachine; // NoContactoContestador
    private Integer mute; // NoContactoNoHablo
    private Integer answeredByAgent; // AgenteAtendidas
    private Integer notAnsweredByAgent; // AgenteAbandonadas
    private Integer notTransferredToAgent; // NoDerivadas
    private Integer inbound; //Entrantes
    private Integer transferredToAgent; // Derivadas
    private Integer abandoned; // Abandonadas
    private Integer incomingTransfer; // TransferIn
    private Integer outboundTransfer; // TransferOut
    private Integer inboundOverflow; // FlowIn
    private Integer outboundOverflow; // FlowOut
    private Integer inboundBeforeSLA; //NSAtendidasAntes + NSAbandonadasAntes
    private Integer inboundAfterSLA; //NSAtendidasDespues + NSAbandonadasDespues

    public InteractionAccumulator() {
    }

    public InteractionAccumulator(Integer attempted, Integer notExecuted, Integer notContacted, Integer contacted, Integer busy, Integer noAnswer, Integer notContactedByOtherReason, Integer fax, Integer answeringMachine, Integer mute, Integer answeredByAgent, Integer notAnsweredByAgent, Integer notTransferredToAgent, Integer inbound, Integer transferredToAgent, Integer abandoned, Integer incomingTransfer, Integer outboundTransfer, Integer inboundOverflow, Integer outboundOverflow, Integer inboundBeforeSLA, Integer inboundAfterSLA) {
        this.attempted = attempted;
        this.notExecuted = notExecuted;
        this.notContacted = notContacted;
        this.contacted = contacted;
        this.busy = busy;
        this.noAnswer = noAnswer;
        this.notContactedByOtherReason = notContactedByOtherReason;
        this.fax = fax;
        this.answeringMachine = answeringMachine;
        this.mute = mute;
        this.answeredByAgent = answeredByAgent;
        this.notAnsweredByAgent = notAnsweredByAgent;
        this.notTransferredToAgent = notTransferredToAgent;
        this.inbound = inbound;
        this.transferredToAgent = transferredToAgent;
        this.abandoned = abandoned;
        this.incomingTransfer = incomingTransfer;
        this.outboundTransfer = outboundTransfer;
        this.inboundOverflow = inboundOverflow;
        this.outboundOverflow = outboundOverflow;
        this.inboundBeforeSLA = inboundBeforeSLA;
        this.inboundAfterSLA = inboundAfterSLA;
    }

    public Integer getAttempted() {
        return attempted;
    }

    public Integer getNotExecuted() {
        return notExecuted;
    }

    public Integer getNotContacted() {
        return notContacted;
    }

    public Integer getContacted() {
        return contacted;
    }

    public Integer getBusy() {
        return busy;
    }

    public Integer getNoAnswer() {
        return noAnswer;
    }

    public Integer getNotContactedByOtherReason() {
        return notContactedByOtherReason;
    }

    public Integer getFax() {
        return fax;
    }

    public Integer getAnsweringMachine() {
        return answeringMachine;
    }

    public Integer getMute() {
        return mute;
    }

    public Integer getAnsweredByAgent() {
        return answeredByAgent;
    }

    public Integer getNotAnsweredByAgent() {
        return notAnsweredByAgent;
    }

    public Integer getNotTransferredToAgent() {
        return notTransferredToAgent;
    }

    public Integer getInbound() {
        return inbound;
    }

    public Integer getTransferredToAgent() {
        return transferredToAgent;
    }

    public Integer getAbandoned() {
        return abandoned;
    }

    public Integer getIncomingTransfer() {
        return incomingTransfer;
    }

    public Integer getOutboundTransfer() {
        return outboundTransfer;
    }

    public Integer getInboundOverflow() {
        return inboundOverflow;
    }

    public Integer getOutboundOverflow() {
        return outboundOverflow;
    }

    public Integer getInboundBeforeSLA() {
        return inboundBeforeSLA;
    }

    public Integer getInboundAfterSLA() {
        return inboundAfterSLA;
    }
}