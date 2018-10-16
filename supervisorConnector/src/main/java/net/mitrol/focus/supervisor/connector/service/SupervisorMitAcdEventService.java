package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.util.ESUtil;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.mitct.mitacd.event.AgentCampaignRelationEvent;
import net.mitrol.mitct.mitacd.event.AgentEvent;
import net.mitrol.mitct.mitacd.event.InteractionEvent;
import net.mitrol.mitct.mitacd.event.MitAcdEvent;
import net.mitrol.utils.ExecutorBuilder;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ladassus
 */
@Service
public class SupervisorMitAcdEventService implements SupervisorMitAcdEvent {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SupervisorMitAcdEventService.class);

    @Value("${index.type:_doc}")
    private String index_type;
    @Value("${index.agent:agent}")
    private String index_agent;
    @Value("${index.interaction:interaction}")
    private String index_interaction;
    @Value("${index_agent_campaign_relation:agentCampaignRelation}")
    private String index_agent_campaign_relation;
    @Value("${bulk_scheduler_delay:10}")
    private long bulkDelay;

    @Autowired
    private ESHighLevelClientService esService;

    private Map map = new HashMap<String, Set<Object>>();
    private ScheduledExecutorService scheduler = ExecutorBuilder.buildNewSingleScheduledExecutorService("MitAcdExecutor");


    @PostConstruct
    public void init() {
        this.scheduler.scheduleWithFixedDelay(() -> {bulk(true);}, 0, this.bulkDelay, TimeUnit.SECONDS);
    }

    @Override
    public void processEvent(MitAcdEvent event) {
        Validate.notNull(event, "Event Request cannot be null");
        String type = event.getType();
        String payload = event.getPayload();
        if (type != null) {
            switch (type) {
                case AgentEvent.TYPE:{
                    bulk(false, index_agent, payload, AgentEvent.class);
                    break;
                }
                case InteractionEvent.TYPE: {
                    bulk(false, index_interaction, payload, InteractionEvent.class);
                    break;
                }
                case AgentCampaignRelationEvent.TYPE: {
                    bulk(false, index_agent_campaign_relation, payload, AgentCampaignRelationEvent.class);
                    break;
                }
                default:
                    break;
            }
        }
    }

    private synchronized void bulk (boolean persist, Object... args){
        if (persist){
            if (!map.isEmpty()) {
                esService.bulkSetOfDocuments(map);
                map.clear();
            }
        } else {
            try {
                Validate.notEmpty(args);
                String index =  ESUtil.getESIndexNameDateValue((String) args[0]);
                Object obj = JsonMapper.getInstance().getObjectFromString((String) args[1], (Class) args[2]);
                Set<Object> objs = (Set) map.get(index);
                if (objs == null) {
                    objs = new HashSet();
                    map.put(index, objs);
                }
                ((Set) objs).add(obj);
            } catch (JSONException e) {
                logger.error(e);
            }
        }
    }
}
