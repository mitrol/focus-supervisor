package net.mitrol.focus.supervisor.core.service.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ESRepository {

    private static MitrolLogger log = MitrolLoggerImpl.getLogger(ESRepository.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * Create an index document in Elasticsearch
     *
     * @param data document to insert
     * @param index
     * @param type document
     * @param id document, this is optional
     * @return id created
     */
    public String buildIndexByParam(Map<String, Object> data, String index, String type, String id) {
        IndexRequest indexRequest;
        if (StringUtils.isEmpty(id)) {
            indexRequest = new IndexRequest(index, type).source(data);
        } else {
            indexRequest = new IndexRequest(index, type, id).source(data);
        }
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return response.getId();
        } catch (ElasticsearchException |  IOException e) {
            throw new MitrolSupervisorError("Unable to create an index in Elasticsearch", e);
        }
    }

    /**
     * Create an index document in Elasticsearch
     *
     * @param data document to insert relation from Entity object complex
     * @param index document
     * @param type document
     * @param id document, this is optional
     * @return id created
     */
    public String buildDocumentIndex(Object data, String index, String type, String id) {
        IndexRequest indexRequest;
        try {
            byte[] json = MAPPER.writeValueAsBytes(data);
            if (StringUtils.isEmpty(id)) {
                indexRequest = new IndexRequest(index, type).source(json, XContentType.JSON);
            } else {
                indexRequest = new IndexRequest(index, type, id).source(json, XContentType.JSON);
            }

            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return response.getId();
        } catch ( ElasticsearchException  | IOException e) {
            throw new MitrolSupervisorError("Unable to create an index in Elasticsearch", e);
        }
    }

    public void bulkSetOfDocuments(Map<String, Set<Object>> docs) {
        BulkRequest request = new BulkRequest();
        docs.forEach((index, objs) -> {
            objs.forEach(obj-> {
                try {
                    byte[] json = MAPPER.writeValueAsBytes(obj);
                    request.add(new IndexRequest(index, "_doc").source(json, XContentType.JSON));
                } catch (JsonProcessingException e) {
                    log.error(e);
                }
            });
        });
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to do a bulk in Elasticsearch", e);
        }
    }

    /**
     * Update an index document in Elasticsearch
     *
     * @param data document to insert
     * @param index
     * @param type document
     * @param id document
     * @return id created
     */
    public void upsertDocumentIndexAsync(Object data , String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).docAsUpsert(true);
        try {
            byte[] json = MAPPER.writeValueAsBytes(data);
            updateRequest.doc(json, XContentType.JSON);
            restHighLevelClient.updateAsync(updateRequest, RequestOptions.DEFAULT, new ActionListener<UpdateResponse>() {
                @Override
                public void onResponse(UpdateResponse updateResponse) {
                    log.debug("Index upsert in Elasticsearch: " + updateResponse.getId());
                }

                @Override
                public void onFailure(Exception e) {
                    throw new MitrolSupervisorError("Unable to upsert an index in Elasticsearch", e);

                }
            });
        } catch (IOException e){
            throw new MitrolSupervisorError("Unable to do an upsert in Elasticsearch", e);
        }
    }

    /**
     * Create an index document asynchronously in Elasticsearch
     *
     * @param data document to insert
     * @param index
     * @param type document
     * @param id document
     */
    public void buildIndexByParamAsync(Map<String, Object> data, String index, String type, String id) {
        IndexRequest indexRequest = new IndexRequest(index, type, id).source(data);
        restHighLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.debug("Index created in Elasticsearch: " + indexResponse.getId());
            }

            @Override
            public void onFailure(Exception e) {
                throw new MitrolSupervisorError("Unable to create an index in Elasticsearch", e);
            }
        });
    }

    /**
     * Search data document in Elasticsearch
     *
     * @param index
     * @param type document
     * @param id document
     * @return data source
     */
    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            return null;
        }
        GetRequest getRequest = new GetRequest(index, type, id);
        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            return getResponse.getSourceAsMap();
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch", e);
        }
    }

    /**
     * Search data document in Elasticsearch and mapper generic
     *
     * @param index
     * @param type document
     * @param valueType Class type
     * @return T List<T>
     */
    public <T> List<T> searchDataByIndex(String index, String type, Class<T> valueType) {
        if(index == null || type == null) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(searchSourceBuilder);
            searchRequest.indices(index);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return getObjects(searchResponse, valueType);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    /**
     * Search data document in Elasticsearch and mapper generic
     *
     * @param index
     * @param type document
     * @param valueType Class type
     * @return T List<T>
     */
    public <T> List<T> searchDataByQuery(String index, String type, Class<T> valueType, AbstractQueryBuilder matchQueryBuilder) {
        if(index == null || type == null) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if (null != matchQueryBuilder) searchSourceBuilder.query(matchQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            searchRequest.indices(index);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return getObjects(searchResponse, valueType);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    /*
    * AndAggregation
    * */
    public <T> List<T> searchDataByQueryAndAggregation(String index, String type, Class<T> valueType, SearchSourceBuilder searchSourceBuilder) {
        if(index == null || type == null) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(searchSourceBuilder);
            searchRequest.indices(index);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return getObjectsAggregation(searchResponse, valueType);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    /*
    * multipleSearchDataByQueryAndAggregation
    * */
    public <T> List<T> multipleSearchDataByQueryAndAggregation(String index, String type, Class<T> valueType, List<SearchSourceBuilder> searchSourceBuilders) {
        if(index == null || type == null) {
            return null;
        }
        try {
            MultiSearchRequest request = new MultiSearchRequest();
            for (SearchSourceBuilder searchSourceBuilder : searchSourceBuilders) {
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.source(searchSourceBuilder);
                searchRequest.indices(index);
                request.add(searchRequest);
            }
            MultiSearchResponse multiSearchResponse = restHighLevelClient.msearch(request, RequestOptions.DEFAULT);
            return getMultipleSearchAggregation(multiSearchResponse, valueType);
        } catch (IOException | JSONException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    /**
     * Delete a index document in Elasticsearch
     *
     * @param index
     * @param type document
     * @param id document
     */
    public void deleteDataByParam(String index, String type, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e){
            throw new MitrolSupervisorError("Unable to remove index in Elasticsearch", e);
        }
    }

    /**
     * Verify existence of the index.
     *
     * @param index
     * @return true if exist the index, otherwise false
     */
    public boolean exists (String index, String type, String id) {
        GetRequest getRequest = new GetRequest(index, type, id);
        getRequest.fetchSourceContext(new FetchSourceContext(false)); //Disable fetching _source.
        getRequest.storedFields("_none_"); //Disable fetching stored fields.
        try {
            return restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to verify if an index exist in Elasticsearch", e);
        }
    }

    private static <T> List<T> getObjects(SearchResponse response, Class<T> valueType) throws JSONException {
        List<T> res = Lists.newArrayList();
        for(SearchHit hit:response.getHits()){
            res.add(getObject(hit, valueType));
        }
        return res;
    }

    private static <T> List<T> getObjectsAggregation(SearchResponse response, Class<T> valueType) throws JSONException {
        List<T> res = Lists.newArrayList();
        for(Aggregation aggregation : response.getAggregations()){
            HashMap<String, Long> data = new HashMap();
            data.put(aggregation.getName(), ((ParsedValueCount) aggregation).getValue());
            res.add((T) data);
        }
        return res;
    }

    private static <T> List<T> getMultipleSearchAggregation(MultiSearchResponse response, Class<T> valueType) throws JSONException {
        List<T> res = Lists.newArrayList();
        for(MultiSearchResponse.Item item : response.getResponses()){
            res.add((T) getObjectsAggregation(item.getResponse(), null));
        }
        return res;
    }

    private static <T> T getObject(SearchHit hit, Class<T> valueType) throws JSONException {
        return JsonMapper.getInstance().getObjectFromString(hit.getSourceAsString(), valueType);
    }

    private static <T> T getObject(GetResponse response, Class<T> valueType) throws JSONException {
        return JsonMapper.getInstance().getObjectFromString(response.getSourceAsString(), valueType);
    }
}
