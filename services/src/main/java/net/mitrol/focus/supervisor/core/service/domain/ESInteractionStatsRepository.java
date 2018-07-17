package net.mitrol.focus.supervisor.core.service.domain;

import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.focus.supervisor.models.InteractionState;
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
    public List<HashMap> countInteractionStatsByCampaign(String date, String campaignId) {
        if(date == null) {
            return null;
        }
        try {
            MultiSearchRequest request = new MultiSearchRequest();
            /*Search by TALKING*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.TALKING.name()));
            /*Search by RINGING*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.RINGING.name()));
            /*Search by PREVIEW*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.PREVIEW.name()));
            /*Search by DIAL*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.DIALING_DIALER.name()));
            /*Search by HOLD*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.HOLD.name()));
            /*Search by ACW*/
            request.add(makeSearchFilterByRangeCampaignIdInteractionState(index_interaction + SEPARATOR + SEARCH_ALL_INDEX, campaignId, InteractionState.AFTER_CALL_WORK.name()));

            MultiSearchResponse multiSearchResponse = restHighLevelClient.multiSearch(request);
            return getMultipleSearchAggregation(multiSearchResponse);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private List<HashMap> getMultipleSearchAggregation(MultiSearchResponse response) throws JSONException {
        List<HashMap> res = Lists.newArrayList();
        for(MultiSearchResponse.Item item : response.getResponses()){
            for(Aggregation aggregation : item.getResponse().getAggregations()) {
                HashMap<String, Long> data = new HashMap();
                data.put(aggregation.getName(), ((ParsedValueCount) aggregation).getValue());
                res.add(data);
            }
        }
        return res;
    }

    private SearchRequest makeSearchFilterByRangeCampaignIdInteractionState(String index, String campaignId, String state) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                .filter(QueryBuilders.rangeQuery("date.keyword").gte("13/07/2018 12:58").lte("13/07/2018 12:59"))
                .filter(QueryBuilders.matchQuery("interactionStats.state.keyword", state))
                .filter(QueryBuilders.matchQuery("interactionStats.campaignId", campaignId));
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count(state).field("interactionStats.state.keyword");
        searchSourceBuilder.query(query).aggregation(aggregationBuildersTotal);
        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }
}