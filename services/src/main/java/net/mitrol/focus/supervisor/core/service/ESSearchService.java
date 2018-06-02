package net.mitrol.focus.supervisor.core.service;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author ladassus
 */
public interface ESSearchService {

    public boolean buildIndex(String index);

    public boolean deleteIndex(String index);

    public Map<String, Object> searchDataByParam(String index, String type, String id);

    public void updateDataById(JSONObject data, String index, String type, String id);

    public String addTargetDataALL(JSONObject data, String index, String type, String id);

    public void deleteDataById(String index, String type, String id);

    public boolean isIndexExist(String index);
}
