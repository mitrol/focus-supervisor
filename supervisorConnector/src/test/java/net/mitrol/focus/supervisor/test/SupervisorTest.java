package net.mitrol.focus.supervisor.test;

import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.connector.service.SupervisorEventService;
import net.mitrol.kafka.KafkaContext;
import net.mitrol.focus.supervisor.test.config.TestConfig;
import net.mitrol.utils.json.JsonMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, KafkaContext.class})
public class SupervisorTest {

    @Autowired
    SupervisorEventService eventService;

    String eventMessage;
    String eventMessage2;
    String eventMessage3;

    @Before
    public void setUp() {
        eventMessage = "{\"id\": 1,\"eventType\": \"subscribe\",\"refreshInterval\": 2,\"widgetType\": \"INTERACTION_STATES\",\"agentId\": 5,\"filter\": {\"campaignId\": [1, 2, 3],\"groupId\": 1,\"dateTo\": \"21-05-2018 12:00:00\",\"dateFrom\": \"27-05-2018 11:45:30\"}}";
        eventMessage2 = "{\"id\": 2,\"eventType\": \"subscribe\",\"refreshInterval\": 2,\"widgetType\": \"AGENT_STATES\",\"agentId\": 6,\"filter\": {\"campaignId\": [1, 2, 3],\"groupId\": 1,\"dateTo\": \"21-05-2018 12:00:00\",\"dateFrom\": \"27-05-2018 11:45:30\"}}";
        eventMessage3 = "{\"id\": 3,\"eventType\": \"unsubscribe\",\"refreshInterval\": 2,\"widgetType\": \"AGENT_STATES\",\"agentId\": 6,\"filter\": {\"campaignId\": [1, 2, 3],\"groupId\": 1,\"dateTo\": \"21-05-2018 12:00:00\",\"dateFrom\": \"27-05-2018 11:45:30\"}}";
    }

    @Test
    @Ignore
    public void test1() throws InterruptedException, JSONException {
        EventRequest event = JsonMapper.getInstance().getObjectFromString(eventMessage, EventRequest.class);
        eventService.processEvent(event);
        while (true){

        }
        //eventService.eventMessageProcess(eventMessage2);
        //Thread.sleep(20000);
        //eventService.eventMessageProcess(eventMessage3);
        //Thread.sleep(20000);
        //Assert.assertTrue(true);
    }
}