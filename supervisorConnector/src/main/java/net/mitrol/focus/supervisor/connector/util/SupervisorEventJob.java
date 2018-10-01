package net.mitrol.focus.supervisor.connector.util;

import net.mitrol.focus.supervisor.common.enums.WidgetType;
import net.mitrol.focus.supervisor.common.event.EventDataValue;
import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.common.event.EventResponse;
import net.mitrol.focus.supervisor.common.event.EventValues;
import net.mitrol.focus.supervisor.connector.service.SupervisorKafkaService;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.quartz.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ladassus
 */
public class SupervisorEventJob implements Job {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorEventJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Getting Job Data Map
        JobDataMap data = context.getJobDetail().getJobDataMap();

        // Getting EventRequest from Job Data Map
        EventRequest eventRequest = (EventRequest) data.get("event");

        // Getting Services from Job Data Map
        // TODO: ESGenericService esService = (ESGenericService) data.get("esService");
        SupervisorKafkaService kafkaService = (SupervisorKafkaService) data.get("kafkaService");
        String kafkaTopicName = (String) data.get("kafkaTopicName");

        // Getting query from Elasticsearch
        // TODO:EventResponse eventResponse = esService.getEventResponse(eventRequest);
        EventResponse eventResponse = DummyDataResponse.getEventReponse(eventRequest);

        if (eventResponse !=null) {
            // Building string message to response
            String strResponse = JsonMapper.getInstance().getStringJsonFromObject(eventResponse);
            // Sending message response to kafka
            kafkaService.sender(kafkaTopicName, strResponse);
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
            WidgetType widgetType = WidgetType.valueOf(eventRequest.getWidgetType().toUpperCase());
            switch (widgetType) {
                case INTERACTION_STATES:{
                    int[] ids = {0,1,2,3,4,5,6};
                    widget.setValues(getValues(ids));
                    break;
                }
                case AGENT_STATES:{
                    int[] ids = {0,1,2,3,4,5,6,7,8,9,10};
                    widget.setValues(getValues(ids));
                    break;
                }
                case AUXILIARY_STATES:{
                    break;
                }
                case STATES_TIME_COUNTER:{
                    break;
                }
                case AUXILIARY_STATES_TIME_COUNTER:{
                    break;
                }
                case AGENT_INFORMATION:{
                    break;
                }
                case INTERACTIONS_COUNTER:{
                    break;
                }
                case INTERACTIONS_DETAILS_COUNTER:{
                    break;
                }
                case SKILL_INDICATORS:{
                    break;
                }
                case AGENT_INTERACTION_STATES:{
                    break;
                }
                case SKILL_INTERACTIONS_COUNTER:{
                    break;
                }
                case AGENT_INTERACTIONS_COUNTER:{
                    break;
                }
                default:
                    break;
            }
            eventResponse.setWidgetValues(widget);
            return eventResponse;
        }
        
        private static void addIds (int[] ids, List list){
            for (int arrElement : ids) {
                EventDataValue dataValue = new EventDataValue();
                dataValue.setId(arrElement);
                dataValue.setValue(ThreadLocalRandom.current().nextInt(0, 10));
                list.add(dataValue);
            }
        }

        private static List<EventDataValue> getValues (int[] ids){
            List<EventDataValue> list = new ArrayList<EventDataValue>();
            addIds(ids, list);
            return list;
        }
    }
}
