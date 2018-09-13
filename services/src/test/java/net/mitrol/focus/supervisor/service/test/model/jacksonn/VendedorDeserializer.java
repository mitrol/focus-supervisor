package net.mitrol.focus.supervisor.service.test.model.jacksonn;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.mitrol.ct.api.enums.AgentState;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class VendedorDeserializer extends JsonDeserializer<HashMap<AgentState, Duration>> {

    @Override
    public HashMap<AgentState, Duration> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode root = mapper.readTree(p);
        HashMap<AgentState, Duration> data = new HashMap<>();
        Duration duration = Duration.ofSeconds(root.get("NOT_READY").get("seconds").asLong(), root.get("NOT_READY").get("nano").asInt());
        data.put(AgentState.NotReady, duration);
        return data;
    }
}
