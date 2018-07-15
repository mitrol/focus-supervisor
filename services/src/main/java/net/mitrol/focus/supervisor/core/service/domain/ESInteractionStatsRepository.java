package net.mitrol.focus.supervisor.core.service.domain;

import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.focus.supervisor.models.InteractionState;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Repository
public class ESInteractionStatsRepository extends ESRepository {

    public List<HashMap> countInteractionStatsByCampaign(String index, String campaignId) {
        try {
            MultiSearchRequest request = new MultiSearchRequest();
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.TALKING.name()));
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.RINGING.name()));
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.PREVIEW.name()));
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.DIALING_DIALER.name()));
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.HOLD.name()));
            request.add(getIteractionSearchRequest(index, campaignId, InteractionState.AFTER_CALL_WORK.name()));

            MultiSearchResponse response = restHighLevelClient.multiSearch(request);
            return getMultipleSearchAggregation(response);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private SearchRequest getIteractionSearchRequest (String index, String campaignId, String state){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        AbstractQueryBuilder abstractQueryBuilder = QueryBuilders.matchQuery("interactionStats.state.keyword", state);
        ValueCountAggregationBuilder aggregationBuilders = AggregationBuilders.count(state).field("interactionStats.state.keyword");
        searchBuilder.query(abstractQueryBuilder).aggregation(aggregationBuilders);
        searchRequest.source(searchBuilder);
        return searchRequest;
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
}