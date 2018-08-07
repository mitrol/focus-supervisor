package net.mitrol.focus.supervisor.mitacd.connector_generator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mitrol.ct.api.enums.AgentState;
import net.mitrol.ct.api.enums.Channel;
import net.mitrol.ct.api.enums.InteractionState;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class WebhookService {

    private MitAcdConnectorService mitAcdConnectorService;
    private ObjectMapper objectMapper;

    @Autowired
    public WebhookService() {
    }

    public void sendAgentEvent() throws JsonProcessingException {
        sendEvent(new MitAcdEvent(AgentEvent.TYPE,
                objectMapper.writeValueAsString(
                        new AgentEvent(new Date(), 1, 1, AgentState.Avail))));
    }

    public void sendInteractionEvent() throws JsonProcessingException {

        LocalDateTime now = LocalDateTime.now();
        String interactionId = String.format("%02d%02d%02d%02d%02d%02d%03d_ACD_00000",
                now.getYear() % 20, now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getNano() / 1000 / 1000);

        sendEvent(new MitAcdEvent(InteractionEvent.TYPE,
                objectMapper.writeValueAsString(
                        new InteractionEvent(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()), interactionId, 1, InteractionState.Talking, 1, 1, 1, null, null, null, null, Channel.Llamada, null, null))));
    }

    private void sendEvent(MitAcdEvent event) throws JsonProcessingException {
        mitAcdConnectorService.postEvent(
                objectMapper.writeValueAsString(event));
    }
}
