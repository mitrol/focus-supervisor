package net.mitrol.focus.supervisor.core.service.domain;

import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.mitct.mitacd.event.AgentState;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
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
import java.util.HashMap;
import java.util.List;

@Repository
public class ESAgentStateRepository {

    private static final String SEPARATOR = "-";

    /**
     * This String is used to try search with pattern indexes
     * Ex: index_interaction + SEPARATOR + SEARCH_ALL_INDEX to set in indices in searchRequest
     * */
    private static final String SEARCH_ALL_INDEX = "*";

    @Value("${index_agent}")
    private String index_agent_status;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @param date       format YYYY.mm.dd
     * @param campaignIds to search and filter by id campaign
     * TODO Add range date to search, gte and lte.
     **/
    public List<HashMap> countAgentStatus(String date, List<Integer> campaignIds, String companyId,
                                          String agentId, boolean searchAllIndex, long from, long to) {
        StringBuilder indexBuild = new StringBuilder(index_agent_status + SEPARATOR + date);
        if (searchAllIndex) {
            indexBuild.append(SEARCH_ALL_INDEX);
        }
        String index = indexBuild.toString();
        try {
            MultiSearchRequest request = new MultiSearchRequest();
            /*Search by Avail*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Avail.name(), companyId, agentId, from, to));
            /*Search by Preview*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Preview.name(), companyId, agentId, from, to));
            /*Search by Dial by Agente*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Dial.name(), companyId, agentId, from, to));
            /*Search Ring*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Ring.name(), companyId, agentId, from, to));
            /*Search by Connect*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Connect.name(), companyId, agentId, from, to));
            /*Search by Hold*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.Hold.name(), companyId, agentId, from, to));
            /*Search by AfterCallWork*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.AfterCallWork.name(), companyId, agentId, from, to));
            /*Search by NotReady*/
            request.add(makeSearchFilterAgentStatus(index, campaignIds, AgentState.NotReady.name(), companyId, agentId, from, to));
            /*Search by Auxiliar BREAK_0 To Other BEAK_n*/
            request.add(searchByAuxiliar(index, campaignIds, companyId, agentId, from, to));
            /*Search by Other*/
            //request.add(makeSearchFilterAgentStatus(index, campaignId, AgentState.HOLD.name(), companyId, agentId, from, to));
            MultiSearchResponse multiSearchResponse = restHighLevelClient.multiSearch(request);
            return getMultipleSearchAggregation(multiSearchResponse);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private List<HashMap> getMultipleSearchAggregation(MultiSearchResponse response) throws JSONException {
        List<HashMap> res = Lists.newArrayList();
        for (MultiSearchResponse.Item item : response.getResponses()) {
            if (null != item.getResponse() || null == item.getFailure()) {
                for (Aggregation aggregation : item.getResponse().getAggregations()) {
                    HashMap<String, Long> data = new HashMap();
                    data.put(aggregation.getName(), ((ParsedValueCount) aggregation).getValue());
                    res.add(data);
                }
            }
        }
        return res;
    }

    /**
     * @param campaignIds represent userIds referent relation Agent and Campaign
     * */
    private SearchRequest makeSearchFilterAgentStatus(String index, List<Integer> campaignIds, String state, String companyId, String agentId, Long from, Long to) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(campaignIds)) {
            /**
             * En el should deberia venir una coleccion de los userID que se extrajeron antes de AgentCampaingRelation
             * */
            query.should(QueryBuilders.termsQuery("userId", campaignIds));
            /**
             * Esto deberia Eliminarse luego
             */
            //query.filter(QueryBuilders.matchQuery("campaignId", campaignId));
        }
        if (!StringUtils.isEmpty(companyId)) {
            query.filter(QueryBuilders.matchQuery("companyId", companyId));
        }
        if (!StringUtils.isEmpty(agentId)) {
            query.filter(QueryBuilders.matchQuery("userId", agentId));
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

    private SearchRequest searchByAuxiliar(String index, List<Integer> campaignIds, String companyId, String agentId, Long from, Long to) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(campaignIds)) {
            /**
             * En el should deberia venir una coleccion de los userID que se extrajeron antes de AgentCampaingRelation
             * */
            query.should(QueryBuilders.termsQuery("userId", campaignIds));
            /**
             * Esto deberia Eliminarse luego
             */
            //query.filter(QueryBuilders.matchQuery("campaignId", campaignId));
        }
        if (!StringUtils.isEmpty(companyId)) {
            query.filter(QueryBuilders.matchQuery("companyId", companyId));
        }
        if (!StringUtils.isEmpty(agentId)) {
            query.filter(QueryBuilders.matchQuery("userId", agentId));
        }
        if (null != from && null != to) {
            /**
             * Este rango hay que probar todavia
             * */
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("timestamp").gte(from).lte(to);
            query.filter(rangeQueryBuilder);
        }
        query.filter(QueryBuilders.termsQuery("state.keyword", AgentState.Break0.name(), AgentState.Break1.name(), AgentState.Break2.name(), AgentState.Break3.name(), AgentState.Break4.name(), AgentState.Break5.name(),
                AgentState.Break6.name(), AgentState.Break7.name(), AgentState.Break8.name(), AgentState.Break9.name()));
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count("Auxiliar").field("state.keyword");
        searchSourceBuilder.query(query).aggregation(aggregationBuildersTotal);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
