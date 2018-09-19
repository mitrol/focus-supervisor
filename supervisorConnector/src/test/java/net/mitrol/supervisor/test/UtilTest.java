package net.mitrol.supervisor.test;

import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.utils.json.JsonMapper;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;

public class UtilTest {

    public static void main (String args[]){
        while (true) {
            String message = "{\"id\": 1,\"eventType\": \"subscribe\",\"refreshInterval\": 2,\"widgetType\": \"INTERACTION_STATES\",\"agentId\": 5,\"filter\": {\"campaignId\": [1, 2, 3],\"groupId\": 1,\"dateTo\": \"21-05-2018 12:00:00\",\"dateFrom\": \"27-05-2018 11:45:30\"}}";
            eventMessageValidate(null);
            System.out.print("hola hola");
        }

    }

    private static EventRequest eventMessageValidate (String message){
        Validate.notNull(message, "Supervisor event message cannot be null");
        EventRequest event = null;
        try {
            event = JsonMapper.getInstance().getObjectFromString(message, EventRequest.class);
        } catch (JSONException e) {
            return event;
        }
        Validate.notNull(event.getId(), "Supervisor event message cannot be null");
        Validate.notNull(event.getAgentId(), "Supervisor event message cannot be null");
        Validate.notNull(event.getWidgetType(), "Supervisor event message cannot be null");
        return event;
    }
}
