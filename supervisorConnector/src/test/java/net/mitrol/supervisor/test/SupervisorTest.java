package net.mitrol.supervisor.test;

import net.mitrol.focus.supervisor.connector.service.SupervisorEventService;
import net.mitrol.kafka.KafkaContext;
import net.mitrol.supervisor.test.config.TestConfig;
import org.junit.Assert;
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


    @Before
    public void setUp() {
        eventMessage = "{\"id\": 1,\"eventType\": \"subscribe\",\"refreshInterval\": 2,\"widgetType\": \"INTERACTION_STATES\",\"agentId\": 5,\"filter\": {\"campaignId\": [1, 2, 3],\"groupId\": 1,\"dateTo\": \"21-05-2018 12:00:00\",\"dateFrom\": \"27-05-2018 11:45:30\"}}";
    }

    @Test
    @Ignore
    public void test_() {
        eventService.eventMessageProcess(eventMessage);
        Assert.assertTrue(true);
    }
}