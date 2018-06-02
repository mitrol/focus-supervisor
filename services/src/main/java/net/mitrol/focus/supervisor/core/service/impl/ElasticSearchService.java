package net.mitrol.focus.supervisor.core.service.impl;

import net.mitrol.focus.supervisor.core.service.ESSearchService;
import net.mitrol.focus.supervisor.core.service.domain.ESRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ladassus
 */
@Service
public class ElasticSearchService implements ESSearchService {

    @Autowired
    private ESRepository eSRepository;

    @Override
    public boolean buildIndex(String index) {
        return eSRepository.buildIndex(index);
    }

    @Override
    public boolean deleteIndex(String index) {
        return eSRepository.deleteIndex(index);
    }

    @Override
    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        return eSRepository.searchDataByParam(index, type, id);
    }

    @Override
    public void updateDataById(JSONObject data, String index, String type, String id) {
        eSRepository.updateDataById(data, index, type, id);
    }

    @Override
    public String addTargetDataALL(JSONObject data, String index, String type, String id) {
        return eSRepository.addTargetDataALL(data, index, type, id);
    }

    @Override
    public void deleteDataById(String index, String type, String id) {
        eSRepository.deleteDataById(index, type, id);
    }

    @Override
    public boolean isIndexExist(String index) {
        return eSRepository.isIndexExist(index);
    }
}
