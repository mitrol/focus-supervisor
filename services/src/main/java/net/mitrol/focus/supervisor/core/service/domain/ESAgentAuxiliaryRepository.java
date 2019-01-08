package net.mitrol.focus.supervisor.core.service.domain;

import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
//import net.mitrol.mitct.mitacd.event.AgentState;
import net.mitrol.focus.supervisor.common.event.EventDataValue;
import net.mitrol.focus.supervisor.mitct.mitacd.event.AgentState;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ESAgentAuxiliaryRepository {

    private static final String SEPARATOR = "-";

    /**
     * This String is used to try search with pattern indexes
     * Ex: index_interaction + SEPARATOR + SEARCH_ALL_INDEX to set in indices in searchRequest
     * */
    private static final String SEARCH_ALL_INDEX = "*";

    @Value("${index.agent:agent}")
    private String index_agent_status;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @param index format YYYY.mm.dd to search by index in elasticsearch, remember that We are save an index by day
     * @param campaignIds to search and filter by id campaign
     * @param companyId
     * @param agentId
     * @param searchAllIndex When is true, the queries are using all index exists
     * @param from range time in long to search gte and lte
     * @param to range time in long to search gte and lte
     **/
    public List<EventDataValue> countAgentAuxiliary(String index, List<String> campaignIds, String companyId,
                                String agentId, boolean searchAllIndex, Long from, Long to) {
        StringBuilder indexBuild = new StringBuilder(index_agent_status + SEPARATOR + index);
        if (searchAllIndex) {
            indexBuild.append(SEARCH_ALL_INDEX);
        }
        String indexes = indexBuild.toString();
        try {
            MultiSearchRequest request = new MultiSearchRequest();
            /*Search by Auxiliary 0*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break0.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 1*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break1.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 2*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break2.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 3*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break3.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 4*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break4.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 5*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break5.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 6*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break6.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 7*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break7.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 8*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break8.name(), companyId, agentId, from, to));
            /*Search by Auxiliary 9*/
            request.add(makeSearchFilterAgentStatus(indexes, campaignIds, AgentState.Break9.name(), companyId, agentId, from, to));

            MultiSearchResponse multiSearchResponse = restHighLevelClient.msearch(request, RequestOptions.DEFAULT);
            return getMultipleSearchAggregation(multiSearchResponse);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private List<EventDataValue> getMultipleSearchAggregation(MultiSearchResponse response) throws JSONException {
        List<EventDataValue> res = new ArrayList<>();
        for (MultiSearchResponse.Item item : response.getResponses()) {
            if (null != item.getResponse() || null == item.getFailure()) {
                for (Aggregation aggregation : item.getResponse().getAggregations()) {
                    EventDataValue event = new EventDataValue();
                    event.setId(AgentState.getFromName(aggregation.getName()).getCode());
                    event.setValue(((ParsedValueCount) aggregation).getValue());
                    res.add(event);
                }
            }
        }
        return res;
    }

    /**
     * @param campaignIds represent userIds referent relation Agent and Campaign
     * */
    private SearchRequest makeSearchFilterAgentStatus(String index, List<String> campaignIds, String state, String companyId, String agentId, Long from, Long to) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(campaignIds)) {
            /**
             * En el should deberia venir una coleccion de los userID que se extrajeron antes de AgentCampaingRelation
             * */
            query.must(QueryBuilders.termsQuery("userId", campaignIds));
        }
        if (!StringUtils.isEmpty(companyId)) {
            query.must(QueryBuilders.matchQuery("companyId", companyId));
        }
        if (!StringUtils.isEmpty(agentId)) {
            query.must(QueryBuilders.matchQuery("userId", agentId));
        }
        if (null != from && null != to) {
            /**
             * Este rango hay que probar todavia
             * */
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("timestamp").gte(from).lte(to);
            query.filter(rangeQueryBuilder);
        }
        query.filter(QueryBuilders.matchQuery("state.keyword", state));
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count(state).field("state.keyword");
        searchSourceBuilder.query(query).aggregation(aggregationBuildersTotal);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
