package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.common.event.EventRequest;
import net.mitrol.focus.supervisor.common.enums.WidgetType;
import net.mitrol.focus.supervisor.common.event.EventResponse;
import net.mitrol.focus.supervisor.common.event.EventValues;
import net.mitrol.focus.supervisor.core.service.domain.ESAgentAuxiliaryRepository;
import net.mitrol.focus.supervisor.core.service.domain.ESAgentStateRepository;
import net.mitrol.focus.supervisor.core.service.domain.ESInteractionStatsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ESGenericService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final String SPLITTER = ".";

    @Autowired
    ESInteractionStatsRepository stateRepository;

    @Autowired
    ESAgentStateRepository esAgentStateRepository;

    @Autowired
    ESAgentAuxiliaryRepository esAgentAuxiliaryRepository;

    public EventResponse getEventResponse(EventRequest eventMessage) {
        EventResponse eventResponse = new EventResponse();
        EventValues eventValues = new EventValues();
        eventValues.setAgentId(eventMessage.getAgentId());
        eventValues.setDashboardId(eventMessage.getDashboardId());
        eventValues.setEventType(eventMessage.getEventType());
        eventValues.setId(eventMessage.getId());
        eventValues.setRefreshInterval(eventMessage.getRefreshInterval());
        eventValues.setWidgetId(eventMessage.getWidgetId());
        eventValues.setWidgetType(eventMessage.getWidgetType());
        WidgetType type = WidgetType.valueOf(eventMessage.getWidgetType().toUpperCase());
        String index = getIndex(eventMessage.getFilter().getDateFrom());
        String dateStarted = eventMessage.getFilter().getDateFrom();
        String dateFinished = eventMessage.getFilter().getDateTo();
        LocalDateTime dateFrom = LocalDateTime.parse(dateStarted, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime dateTo = LocalDateTime.parse(dateFinished, DateTimeFormatter.ofPattern(DATE_FORMAT));
        long from = dateFrom.atZone(ZONE_ID).toEpochSecond();
        long to = dateTo.atZone(ZONE_ID).toEpochSecond();
        boolean searchAllIndex = false;
        if (StringUtils.isEmpty(dateStarted)) {
            searchAllIndex = true;
        }

        switch (type) {
            case INTERACTION_STATES:
                countInteractionStats(index, null, null, null, null, null, searchAllIndex);
                eventResponse.setWidgetValues(eventValues);
                return eventResponse;
            case AGENT_STATES:
                countAgentState(index, eventMessage.getFilter().getCampaignIds(), null, eventMessage.getAgentId(), searchAllIndex, from, to);
                eventResponse.setWidgetValues(eventValues);
                return eventResponse;
            case AUXILIARY_STATES:
                countAgentAuxiliary(index, eventMessage.getFilter().getCampaignIds(), null, eventMessage.getAgentId(), searchAllIndex, from, to);
                eventResponse.setWidgetValues(eventValues);
                return eventResponse;
            case STATES_TIME_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case AUXILIARY_STATES_TIME_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case AGENT_INFORMATION:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case INTERACTIONS_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case INTERACTIONS_DETAILS_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case SKILL_INDICATORS:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case AGENT_INTERACTION_STATES:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case SKILL_INTERACTIONS_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            case AGENT_INTERACTIONS_COUNTER:
                countAgentState(null, null, null, null, false, null, null);
                return eventResponse;
            default:
                return eventResponse;
        }
    }

    public Map countInteractionStats(String index, String campaignId, String companyId, String groupId,
                                     String agentId, String splitId, boolean searchAllIndex) {
        return stateRepository.countInteractionStats(index, campaignId, companyId, groupId, agentId, splitId, searchAllIndex);
    }

    public Map countAgentState(String index, List<Long> campaignIds, String companyId,
                                         String agentId, boolean searchAllIndex, Long from, Long to) {
        return esAgentStateRepository.countAgentStatus(index, campaignIds, companyId, agentId, searchAllIndex, from, to);
    }

    public Map countAgentAuxiliary(String index, List<Long> campaignIds, String companyId,
                               String agentId, boolean searchAllIndex, Long from, Long to) {
        return esAgentAuxiliaryRepository.countAgentAuxiliary(index, campaignIds, companyId, agentId, searchAllIndex, from, to);
    }

    private String getIndex(String dateStarted) {
        return dateStarted.substring(0,4) + SPLITTER + dateStarted.substring(5, 7) + SPLITTER + dateStarted.substring(8, 10);
    }

}
