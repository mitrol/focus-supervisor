package net.mitrol.focus.supervisor.core.service.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.mitrol.focus.supervisor.common.error.MitrolSupervisorError;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
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
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
            return response.getId();
        } catch (ElasticsearchException |  IOException e) {
            throw new MitrolSupervisorError("Unable to create an index in Elasticsearch", e);
        }
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            return null;
        }
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            return sourceAsMap;
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to do a get request in Elasticsearch", e);
        }
    }

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

    public void deleteDataByParam(String index, String type, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
        } catch (IOException e){
            throw new MitrolSupervisorError("Unable to remove index in Elasticsearch", e);
        }
    }

    public void exists (String index, String type, String id) {
        GetRequest getRequest = new GetRequest(index, type, id);
        getRequest.fetchSourceContext(new FetchSourceContext(false)); //Disable fetching _source.
        getRequest.storedFields("_none_"); //Disable fetching stored fields.
        try {
            boolean exists = restHighLevelClient.exists(getRequest);
        } catch (IOException e) {
            throw new MitrolSupervisorError("Unable to verify if an index exist in Elasticsearch", e);
        }
    }
}
