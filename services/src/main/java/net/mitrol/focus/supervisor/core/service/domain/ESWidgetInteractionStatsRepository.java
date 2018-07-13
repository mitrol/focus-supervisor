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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class ESWidgetInteractionStatsRepository extends ESRepository {

    public List<HashMap> findInteractionStatsByCampaign(String index, String campaignId) {
        if(index == null) {
            return null;
        }
        try {
            MultiSearchRequest request = new MultiSearchRequest();

            /*
             *   Search by TALKING
             * */
            SearchRequest searchRequestTALKING = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderTALKING = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilder = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.TALKING.name());
            ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count(InteractionState.TALKING.name()).field("interactionStats.state.keyword");
            searchBuilderTALKING.query(abstractQueryBuilder).aggregation(aggregationBuildersTotal);
            searchRequestTALKING.source(searchBuilderTALKING);
            request.add(searchRequestTALKING);

            /*
             *   Search by RINGING
             * */
            SearchRequest searchRequestRINGING = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderRINGING = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilderRINGING = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.RINGING.name());
            ValueCountAggregationBuilder aggregationBuildersRINGING = AggregationBuilders.count(InteractionState.RINGING.name()).field("interactionStats.state.keyword");
            searchBuilderRINGING.query(abstractQueryBuilderRINGING).aggregation(aggregationBuildersRINGING);
            searchRequestRINGING.source(searchBuilderRINGING);
            request.add(searchRequestRINGING);

            /*
             *   Search by PREVIEW
             * */
            SearchRequest searchRequestPREVIEW = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderPREVIEW = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilderPREVIEW = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.PREVIEW.name());
            ValueCountAggregationBuilder aggregationBuildersPREVIEW = AggregationBuilders.count(InteractionState.PREVIEW.name()).field("interactionStats.state.keyword");
            searchBuilderPREVIEW.query(abstractQueryBuilderPREVIEW).aggregation(aggregationBuildersPREVIEW);
            searchRequestPREVIEW.source(searchBuilderPREVIEW);
            request.add(searchRequestPREVIEW);

            /*
             *   Search by DIAL
             * */
            SearchRequest searchRequestDIAL = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderDIAL = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilderDIAL = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.DIALING_DIALER.name());
            ValueCountAggregationBuilder aggregationBuildersDIAL = AggregationBuilders.count(InteractionState.DIALING_DIALER.name()).field("interactionStats.state.keyword");
            searchBuilderDIAL.query(abstractQueryBuilderDIAL).aggregation(aggregationBuildersDIAL);
            searchRequestDIAL.source(searchBuilderDIAL);
            request.add(searchRequestDIAL);

            /*
             *   Search by HOLD
             * */
            SearchRequest searchRequestHOLD = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderHOLD = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilderHOLD = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.HOLD.name());
            ValueCountAggregationBuilder aggregationBuildersHOLD = AggregationBuilders.count(InteractionState.HOLD.name()).field("interactionStats.state.keyword");
            searchBuilderHOLD.query(abstractQueryBuilderHOLD).aggregation(aggregationBuildersHOLD);
            searchRequestHOLD.source(searchBuilderHOLD);
            request.add(searchRequestHOLD);

            /*
             *   Search by ACW
             * */
            SearchRequest searchRequestACW = new SearchRequest();
            searchRequestTALKING.indices(index);
            SearchSourceBuilder searchBuilderACW = new SearchSourceBuilder();
            AbstractQueryBuilder abstractQueryBuilderACW = QueryBuilders.matchQuery("interactionStats.state.keyword", InteractionState.AFTER_CALL_WORK.name());
            ValueCountAggregationBuilder aggregationBuildersACW = AggregationBuilders.count(InteractionState.AFTER_CALL_WORK.name()).field("interactionStats.state.keyword");
            searchBuilderACW.query(abstractQueryBuilderACW).aggregation(aggregationBuildersACW);
            searchRequestACW.source(searchBuilderACW);
            request.add(searchRequestACW);

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
}
