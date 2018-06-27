package net.mitrol.focus.supervisor.core.service.domain;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Component
public class ESRepository {

    private static MitrolLogger log = MitrolLoggerImpl.getLogger(ESRepository.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private Client client;

    /**
     * Create an index document in Elasticsesarch
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
            IndexResponse response = restHighLevelClient.index(indexRequest);
            return response.getId();
        } catch (ElasticsearchException |  IOException e) {
            throw new MitrolSupervisorError("Unable to create an index in Elasticsearch", e);
        }
    }

    /**
     * Create an index document asynchronously in Elasticsesarch
     *
     * @param data document to insert
     * @param index
     * @param type document
     * @param id document
     */
    public void buildIndexByParamAsync(Map<String, Object> data, String index, String type, String id) {
        IndexRequest indexRequest = new IndexRequest(index, type, id).source(data);
        restHighLevelClient.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
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
        IndexResponse response = client.prepareIndex(index, type).setSource("sdf").execute().actionGet();

        if(index == null || type == null || id == null) {
            return null;
        }
        GetRequest getRequest = new GetRequest(index, type, id);
        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest);
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            return sourceAsMap;
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
    public <T> List<T> searchDataByIndex(String index, String type, Class<T> valueType) throws JsonParseException, JsonMappingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        if(index == null || type == null) {
            return null;
        }
        try {
            SearchRequest searchRequest = new SearchRequest();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(searchSourceBuilder);
            searchRequest.indices(index);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            List<T> dataInforList = Lists.newArrayList();
            for (SearchHit hit : hits.getHits()) {
                T data = MAPPER.readValue(hit.getSourceAsString(), valueType);
                dataInforList.add(data);
            }

            return dataInforList;
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch with index and type", e);
        }
    }

    /**
     * Update an index document in Elasticsesarch
     *
     * @param data document to insert
     * @param index
     * @param type document
     * @param id document
     * @return data source updated
     */
    public Map<String, Object> updateDataByParam(Map<String, Object> data, String index, String type, String id){
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).fetchSource(true);// Fetch Object after its update
        try {
            updateRequest.doc(data, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
            return sourceAsMap;
        } catch (IOException e){
            throw new MitrolSupervisorError("Unable to do an update in Elasticsearch", e);
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
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
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
            return restHighLevelClient.exists(getRequest);
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to verify if an index exist in Elasticsearch", e);
        }
    }
}
