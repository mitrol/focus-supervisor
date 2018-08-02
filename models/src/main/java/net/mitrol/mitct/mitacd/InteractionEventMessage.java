package net.mitrol.mitct.mitacd;

import net.mitrol.ct.api.entities.ContactType;
import net.mitrol.ct.api.enums.Channel;

import java.util.Date;

public class InteractionEventMessage {

    private Date timestamp;
    private String interactionId;
    private int segment;
    private int dialerTaskId;
    private int dialerTaskContactId;
    private int caseId;
    private int messageId;
    private int messagingAccountId;
    private int interactionType; // TTipoLlamada // TODO fix type
    private Channel channel; // tipo contacto

    private int campaignId;
    private int agentId;
    private int groupId;

    private boolean finished; // TODO flag que indica que la llamada terminó
}
