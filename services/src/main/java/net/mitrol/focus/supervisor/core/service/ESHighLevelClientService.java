package net.mitrol.focus.supervisor.core.service;

import net.mitrol.focus.supervisor.core.service.domain.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
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

    public String insertData(Object object, String index, String type, String id) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        return esRepository.insertData(object, index, type, id);
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        return esRepository.searchDataByParam(index, type, id);
    }

    public <T> List<T> searchDataByIndex(String index, String type, Class<T> valueType) throws IllegalAccessException, IOException, InvocationTargetException {
        return esRepository.searchDataByIndex(index, type, valueType);
    }

    public Map<String, Object> updateDataByParam(Map<String, Object> data, String index, String type, String id){
        return esRepository.updateDataByParam(data, index, type, id);
    }

    public void deleteDataByParam(String index, String type, String id) {
        esRepository.deleteDataByParam(index, type, id);
    }

    public boolean exists (String index, String type, String id) {
        return esRepository.exists(index, type, id);
    }
}
