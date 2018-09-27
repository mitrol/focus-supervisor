package net.mitrol.focus.supervisor.connector.util;

import net.mitrol.focus.supervisor.common.event.EventDataValue;
import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.common.event.EventResponse;
import net.mitrol.focus.supervisor.common.event.EventValues;
import net.mitrol.focus.supervisor.connector.service.SupervisorKafkaService;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.quartz.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ladassus
 */
public class SupervisorEventJob implements Job {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Getting Job Details
        JobKey jobKey = context.getJobDetail().getKey();
        logger.debug("SimpleJob says: " + jobKey + " executing at " + Instant.now());

        // Getting Job Data Map
        JobDataMap data = context.getJobDetail().getJobDataMap();

        // Getting EventRequest from Job Data Map
        EventRequest eventRequest = (EventRequest) data.get("event");

        // Getting Services from Job Data Map
        // TODO: ESGenericService esService = (ESGenericService) data.get("esService");
        SupervisorKafkaService kafkaService = (SupervisorKafkaService) data.get("kafkaService");

        // Getting query from Elasticsearch
        // TODO:EventResponse eventResponse = esService.getEventResponse(eventRequest);
        EventResponse eventResponse = DummyDataResponse.getEventReponse(eventRequest);

        if (eventResponse !=null) {
            // Building string message to response
            String strResponse = JsonMapper.getInstance().getStringJsonFromObject(eventResponse);
            logger.debug("Event Message Response: Job key -> " + jobKey + " Message to send -> " + strResponse);

            // Sending message response to kafka
            kafkaService.sender("supervision.event.response", strResponse);
        }
    }

    private static class DummyDataResponse {
        public static EventResponse getEventReponse (EventRequest eventRequest){
            EventResponse eventResponse = new EventResponse();
            EventValues widget = new EventValues();
            widget.setId(eventRequest.getId());
            widget.setWidgetType(eventRequest.getWidgetType());
            widget.setAgentId(eventRequest.getAgentId());
            widget.setDashboardId(eventRequest.getDashboardId());
            List<Integer> scale = Arrays.asList(0, 2, 4, 6, 8, 10);
            widget.setScale(scale);
            widget.setValues(getDataValue());
            eventResponse.setWidgetValues(widget);
            return eventResponse;
        }

        public static List<EventDataValue> getDataValue (){
            List<EventDataValue> list = new ArrayList<EventDataValue>();
            int preview = ThreadLocalRandom.current().nextInt(0,  10);
            int dial = ThreadLocalRandom.current().nextInt(0,  10);
            EventDataValue data1 = new EventDataValue();
            data1.setId("Preview");
            data1.setValue(preview + "");
            EventDataValue data2 = new EventDataValue();
            data2.setId("Dial");
            data2.setValue(dial + "");
            list.add(data1);
            list.add(data2);
            return list;
        }
    }
}
