package net.mitrol.focus.supervisor.core.service;

import feign.RequestLine;
import net.mitrol.focus.supervisor.core.service.dto.ESInfoDTO;
import org.springframework.stereotype.Service;

/**
 * @author ladassus
 */
@Service
public interface ESSearchService {

    @RequestLine("GET /")
    public ESInfoDTO getInfoElasticsearch();


    //TODO: to complete this

    /*public boolean buildIndex(String index);

    public boolean deleteIndex(String index);

    public Map<String, Object> searchDataByParam(String index, String type, String id);

    public void updateDataById(JSONObject data, String index, String type, String id);

    public String addTargetDataALL(JSONObject data, String index, String type, String id);

    public void deleteDataById(String index, String type, String id);

    public boolean isIndexExist(String index);*/
}
