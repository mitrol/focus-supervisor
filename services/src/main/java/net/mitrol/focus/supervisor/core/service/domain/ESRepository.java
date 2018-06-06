package net.mitrol.focus.supervisor.core.service.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ESRepository {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public String buildIndexByParam(Map<String, Object> data, String index, String type, String id) {
        IndexRequest indexRequest = new IndexRequest(index, type, id).source(data);
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return response.getId();
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            return null;
        }
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }

    public Map<String, Object> updateDataByParam(Map<String, Object> data, String index, String type, String id){
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).fetchSource(true);// Fetch Object after its update
        try {
            updateRequest.doc(data, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
            return sourceAsMap;
        }catch (JsonProcessingException e){
            e.getMessage();
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        return (Map<String, Object>) new HashMap<>().put("Error", "Unable to update book");
    }

    public void deleteDataByParam(String index, String type, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
    }
}
