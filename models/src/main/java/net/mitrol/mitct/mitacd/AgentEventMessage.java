package net.mitrol.mitct.mitacd;

import net.mitrol.ct.api.enums.AgentState;

import java.util.Date;
import java.util.List;

public class AgentEventMessage {

    class AgentInteraction{
        private String interactionId;
        private int segment;
    }

    private Date timestamp;
    private int agentId;
    private int groupId;
    private AgentState state;
    private List<Integer> assignedCampaignIds;
    private List<AgentInteraction> interactions;

}
