package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.core.service.domain.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author ladassus
 */
@Service
public class ESHighLevelClientService {

    @Autowired
    private ESRepository esRepository;

    public String buildIndexByParam(Map<String, Object> data, String index, String type, String id) {
        return esRepository.buildIndexByParam(data, index, type, id);
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        return esRepository.searchDataByParam(index, type, id);
    }

    public Map<String, Object> searchDataByIndex(String index, String type) throws IOException {
        return null;//esRepository.searchDataByIndex(index, type);
    }

    public Map<String, Object> updateDataByParam(Map<String, Object> data, String index, String type, String id){
        return esRepository.updateDataByParam(data, index, type, id);
    }

    public void deleteDataByParam(String index, String type, String id) {
        esRepository.deleteDataByParam(index, type, id);
    }
}
