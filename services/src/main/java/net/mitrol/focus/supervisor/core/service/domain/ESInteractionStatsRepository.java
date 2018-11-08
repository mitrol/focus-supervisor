package net.mitrol.focus.supervisor.core.service.domain;

import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.focus.supervisor.common.event.EventDataValue;
//import net.mitrol.mitct.mitacd.event.AgentState;
import net.mitrol.focus.supervisor.mitct.mitacd.event.AgentState;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.ParsedTopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ESInteractionStatsRepository {

    private static final String SEPARATOR = "-";

    /**
     * This String is used to try search with pattern indexes
     * Ex: index_interaction + SEPARATOR + SEARCH_ALL_INDEX to set in indices in searchRequest
     * */
    private static final String SEARCH_ALL_INDEX = "*";

    @Value("${index.interaction.stats:interactionstats}")
    private String index_interaction;

    @Value("${index.agent.status.changed}")
    private String index_agent_status;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @param date format YYYY.mm.dd
     * @param campaignId to search and filter by id campaign
     * TODO Add range date to search, gte and lte.
     **/
    public Map countInteractionStats(String date, String campaignId, String companyId, String groupId,
                                     String agentId, String splitId, boolean searchAllIndex) {
        StringBuilder indexBuild = new StringBuilder("agent" + SEPARATOR + date);
        if (searchAllIndex) {
            indexBuild.append(SEARCH_ALL_INDEX);
        }
        String index = indexBuild.toString();

        try {
            SearchResponse searchResponse = restHighLevelClient.search(makeSearchFilterByInteractionState(index, campaignId, companyId, groupId, agentId, splitId), RequestOptions.DEFAULT);
            return getSearchAggregation(searchResponse);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    private Map getSearchAggregation(SearchResponse response) throws JSONException {
        List<String> names = Lists.newArrayList();
        List<String> breaks = Lists.newArrayList();
        List<EventDataValue> values = Lists.newArrayList();
        List<String> prueba = Lists.newArrayList();

        for(Aggregation aggregation : response.getAggregations()) {
            List<? extends Terms.Bucket> parses = ((ParsedLongTerms) aggregation).getBuckets();
            for (Terms.Bucket parse : parses) {
                ParsedTopHits parsedTopHits = ((ParsedLongTerms.ParsedBucket) parse).getAggregations().get("group_docs");
                SearchHits searchHits = parsedTopHits.getHits();
                SearchHit[] searchHitsArray = searchHits.getHits();
                for (SearchHit searchHit : searchHitsArray) {
                    String status = searchHit.getFields().get("state.keyword").getValues().get(0).toString();
                    EventDataValue eventDataValue = new EventDataValue();
                    prueba.add(String.valueOf(AgentState.getFromName(status).getCode()));
                    //eventDataValue.setValue();
                    if (status.contains("Break")) {
                        breaks.add(status);
                    } else {
                        names.add(status);
                    }
                }
            }
        }

        Map<String, Long> res = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> res2 = prueba.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        res.put("Break", Long.valueOf(breaks.size()));

        return res;
    }

    private SearchRequest makeSearchFilterByInteractionState(String index, String campaignId, String companyId,
                                                             String groupId, String agentId, String splitId) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(campaignId)) {
            query.filter(QueryBuilders.matchQuery("campaignId", campaignId));
        }
        if (!StringUtils.isEmpty(groupId)) {
            query.filter(QueryBuilders.matchQuery("groupId", groupId));
        }
        if (!StringUtils.isEmpty(companyId)) {
            query.filter(QueryBuilders.matchQuery("companyId", companyId));
        }
        if (!StringUtils.isEmpty(agentId)) {
            query.filter(QueryBuilders.matchQuery("userId", agentId));
        }
        if (!StringUtils.isEmpty(splitId)) {
            query.filter(QueryBuilders.matchQuery("splitId", splitId));
        }

        AggregationBuilder aggTotal = AggregationBuilders.terms("group")
                .field("userId")
                .size(2100000000);// Size Max I think is 70000
        List<String> fields = Arrays.asList("state.keyword");
        AggregationBuilder subAggregation = AggregationBuilders.
                topHits("group_docs").
                explain(true).
                size(1).storedFields(fields).
                sort("timestamp", SortOrder.DESC).
                fetchSource(true).
                storedField("state");
        searchSourceBuilder.query(query).aggregation(aggTotal.subAggregation(subAggregation));
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }
}