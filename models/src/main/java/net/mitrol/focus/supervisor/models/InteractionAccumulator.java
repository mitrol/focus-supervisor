package net.mitrol.focus.supervisor.models;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;

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

    private InteractionAccumulator(Integer attempted, Integer notExecuted, Integer notContacted, Integer contacted, Integer busy, Integer noAnswer, Integer notContactedByOtherReason, Integer fax, Integer answeringMachine, Integer mute, Integer answeredByAgent, Integer notAnsweredByAgent, Integer notTransferredToAgent, Integer inbound, Integer transferredToAgent, Integer abandoned, Integer incomingTransfer, Integer outboundTransfer, Integer inboundOverflow, Integer outboundOverflow, Integer inboundBeforeSLA, Integer inboundAfterSLA) {
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

    public static InteractionAccumulator parse(String s) {
        Integer[] values = MitAcdUtils.StrToIntSin0(s);
        return new InteractionAccumulator(
                values.length > 0 ? values[0] : null,
                values.length > 1 ? values[1] : null,
                values.length > 2 ? values[2] : null,
                values.length > 3 ? values[3] : null,
                values.length > 4 ? values[4] : null,
                values.length > 5 ? values[5] : null,
                values.length > 6 ? values[6] : null,
                values.length > 7 ? values[7] : null,
                values.length > 8 ? values[8] : null,
                values.length > 9 ? values[9] : null,
                values.length > 10 ? values[10] : null,
                values.length > 11 ? values[11] : null,
                values.length > 12 ? values[12] : null,
                values.length > 13 ? values[13] : null,
                values.length > 14 ? values[14] : null,
                values.length > 15 ? values[15] : null,
                values.length > 16 ? values[16] : null,
                values.length > 17 ? values[17] : null,
                values.length > 18 ? values[18] : null,
                values.length > 19 ? values[19] : null,
                values.length > 20 ? values[20] : null,
                values.length > 21 ? values[21] : null);
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