package net.mitrol.focus.supervisor.core.service.domain;

import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.focus.supervisor.models.InteractionState;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Repository
public class ESInteractionStatsRepository extends ESRepository {

    private static final String SEPARATOR = "-";

    /**
     * This String is used to try search with pattern indexes
     * Ex: index_interaction + SEPARATOR + SEARCH_ALL_INDEX to set in indices in searchRequest
     * */
    private static final String SEARCH_ALL_INDEX = "*";

    @Value("${index.interaction.stats}")
    private String index_interaction;

    /**
     * @param date format dd-mm-YYYY
     * @param campaignId to search and filter by id campaign
     * TODO Add range date to search, gte and lte.
     **/
    public List<HashMap> countInteractionStatsByCampaign(String date, String campaignId, String companyId, String groupId,
                                                         String agentId, String batchId, boolean searchAllIndex) {
        StringBuilder indexBuild = new StringBuilder(index_interaction + SEPARATOR + date);
        if (searchAllIndex) {
            indexBuild.append(SEARCH_ALL_INDEX);
        }
        String index = indexBuild.toString();

        try {
            MultiSearchRequest request = new MultiSearchRequest();
            /*Search by TALKING*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.TALKING.name(), companyId, groupId, agentId, batchId));
            /*Search by RINGING*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.RINGING.name(), companyId, groupId, agentId, batchId));
            /*Search by PREVIEW*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.PREVIEW.name(), companyId, groupId, agentId, batchId));
            /*Search by DIAL*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.DIALING_DIALER.name(), companyId, groupId, agentId, batchId));
            /*Search by HOLD*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.HOLD.name(), companyId, groupId, agentId, batchId));
            /*Search by ACW*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index, campaignId, InteractionState.AFTER_CALL_WORK.name(), companyId, groupId, agentId, batchId));

            MultiSearchResponse multiSearchResponse = restHighLevelClient.multiSearch(request);
            return getMultipleSearchAggregation(multiSearchResponse);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private List<HashMap> getMultipleSearchAggregation(MultiSearchResponse response) throws JSONException {
        List<HashMap> res = Lists.newArrayList();
        for(MultiSearchResponse.Item item : response.getResponses()){
            if (null != item.getResponse() || null == item.getFailure()) {
                for(Aggregation aggregation : item.getResponse().getAggregations()) {
                    HashMap<String, Long> data = new HashMap();
                    data.put(aggregation.getName(), ((ParsedValueCount) aggregation).getValue());
                    res.add(data);
                }
            }
        }
        return res;
    }

    private SearchRequest makeSearchFilterByRangeCampaignIdInteractionState(String index, String campaignId, String state, String companyId,
                                                                            String groupId, String agentId, String batchId) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(campaignId)) {
            query.filter(QueryBuilders.matchQuery("interactionStats.campaignId", campaignId));
        }
        if (!StringUtils.isEmpty(groupId)) {
            query.filter(QueryBuilders.matchQuery("interactionStats.groupId", groupId));
        }
        if (!StringUtils.isEmpty(companyId)) {
            query.filter(QueryBuilders.matchQuery("interactionStats.companyId", companyId));
        }
        if (!StringUtils.isEmpty(agentId)) {
            query.filter(QueryBuilders.matchQuery("interactionStats.agentId", agentId));
        }
        if (!StringUtils.isEmpty(batchId)) {
            query.filter(QueryBuilders.matchQuery("interactionStats.batchId", batchId));
        }

        query.filter(QueryBuilders.matchQuery("interactionStats.state.keyword", state));
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count(state).field("interactionStats.state.keyword");
        searchSourceBuilder.query(query).aggregation(aggregationBuildersTotal);
        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }
}