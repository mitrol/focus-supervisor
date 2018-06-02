package net.mitrol.focus.supervisor.core.service.domain;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author ladassus
 */
@Component
public class ESRepository {

    private MitrolLogger log = MitrolLoggerImpl.getLogger(ESRepository.class);

    @Autowired
    private TransportClient client;

    public boolean buildIndex(String index) {
        if (!isIndexExist(index)) {
            log.info("Index does not exist!");
        }
        CreateIndexResponse buildIndexresponse = client.admin().indices().prepareCreate(index).execute().actionGet();
        log.info("Index to create an index: " + buildIndexresponse.isAcknowledged());

        return buildIndexresponse.isAcknowledged();
    }

   public boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            log.info("Index does not exist!");
        }
        DeleteIndexResponse diResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (diResponse.isAcknowledged()) {
            log.info("Delete index **success** index->>>>>>>" + index);
        } else {
            log.info("Delete index **failed** index->>>>> " + index);
        }
        return diResponse.isAcknowledged();
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        if(index == null || type == null || id == null) {
            log.info("Unable to query data, lacks unique value!");
            return null;
        }
        //To get query data information
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        //There is also a specified time to get the information of the return value, if there is a special need

        return getResponse.getSource();
    }

    public void updateDataById(JSONObject data, String index, String type, String id) {
        if(index == null || type == null || id == null) {
            log.info("Unable to update data, lacks unique value!");
            return;
        }
        UpdateRequest up = new UpdateRequest();
        up.index(index).type(type).id(id).doc(data);

        UpdateResponse response = client.update(up).actionGet();
        log.info("Update data status information, status" + response.status().getStatus());
    }

    public String addTargetDataALL(JSONObject data, String index, String type, String id) {
        //Determine if the next id is empty, set an id if empty
        if(id == null) {
            id = UUID.randomUUID().toString();
        }
        //Formally add data into it
        IndexResponse response = client.prepareIndex(index, type, id).setSource(data).get();

        log.info("addTargetDataALL, add data status:" + response.status().getStatus());

        return response.getId();
    }

    public void deleteDataById(String index, String type, String id) {

        if(index == null || type == null || id == null) {
            log.info("Unable to delete data, missing only value!");
            return;
        }
        //Start deleting data
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();

        log.info("Delete data status，status-->>>>" + response.status().getStatus());
    }

    public boolean isIndexExist(String index) {
        IndicesExistsResponse iep = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        if (iep.isExists()) {
            log.info("This index [" + index + "] already exists in the ES cluster");
        } else {
            log.info("Without this index [" + index + "] ");
        }
        return iep.isExists();
    }
}
