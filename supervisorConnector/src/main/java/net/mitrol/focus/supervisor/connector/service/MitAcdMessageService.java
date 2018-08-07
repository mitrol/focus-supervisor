package net.mitrol.focus.supervisor.connector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mitrol.focus.supervisor.common.util.ESUtil;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MitAcdMessageService {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdMessageService.class);

    @Value("${index.type}")
    private String index_type;
    @Value("${index.agent}")
    private String index_agent;
    @Value("${index.interaction}")
    private String index_interaction;
    @Autowired
    private ESHighLevelClientService esService;

    @Autowired
    private ObjectMapper objectMapper;

    protected final void kafkaMsgProcess(String message) throws IOException {

        MitAcdEvent mitAcdEvent = objectMapper.readValue(message, MitAcdEvent.class);
        String type = mitAcdEvent.getType();
        String payload = mitAcdEvent.getPayload();

        switch (type) {
            case AgentEvent.TYPE:
                processAgentEvent(objectMapper.readValue(payload, AgentEvent.class));
                break;
            case InteractionEvent.TYPE:
                processInteractionEvent(objectMapper.readValue(payload, InteractionEvent.class));
                break;
        }
    }

    private void processAgentEvent(AgentEvent agentEvent) throws IOException {
        esService.buildDocumentIndex(agentEvent, ESUtil.getESIndexNameDateValue(index_agent), index_type, "");
    }

    private void processInteractionEvent(InteractionEvent interactionEvent) throws IOException {
        esService.buildDocumentIndex(interactionEvent, ESUtil.getESIndexNameDateValue(index_interaction), index_type, "");
    }

}
