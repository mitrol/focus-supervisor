package net.mitrol.focus.supervisor.mitacd.connector.client;

import com.google.gson.reflect.TypeToken;
import net.mitrol.utils.json.JsonMapper;
import org.json.JSONException;

import java.lang.reflect.Type;

import java.util.Map;
import java.util.Set;

public class prueba {

    public static void main (String args[]){
        String message ="{\"AgentCampaignRelationEvent\":{\"timestamp\": \"2018-04-25T14:05:15.953Z\",\"userId\": 14,\"campaignId\":4,\"isAssigned\": true}}";
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Set set;
        try {
            Map<String, Object> map = JsonMapper.getInstance().getObjectFromString(message, type);
            System.out.println(map.containsKey("AgentCampaignRelationEvent"));
            System.out.println(map.get("AgentCampaignRelationEvent"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
