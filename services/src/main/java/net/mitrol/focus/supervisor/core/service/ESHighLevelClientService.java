package net.mitrol.focus.supervisor.core.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import net.mitrol.focus.supervisor.core.service.domain.ESRepository;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

    public String buildDocumentIndex(Object object, String index, String type, String id) {
        return esRepository.buildDocumentIndex(object, index, type, id);
    }

    public Map<String, Object> searchDataByParam(String index, String type, String id) {
        return esRepository.searchDataByParam(index, type, id);
    }

    public <T> List<T> searchDataByIndex(String index, String type, Class<T> valueType) {
        return esRepository.searchDataByIndex(index, type, valueType);
    }

    public <T> List<T> searchDataByQuery(String index, String type, Class<T> valueType, AbstractQueryBuilder matchQueryBuilder) {
        return esRepository.searchDataByQuery(index, type, valueType, matchQueryBuilder);
    }

    public <T> List<T> searchDataByQueryAndAggregation(String index, String type, Class<T> valueType, SearchSourceBuilder searchSourceBuilder) {
        return esRepository.searchDataByQueryAndAggregation(index, type, valueType, searchSourceBuilder);
    }

    public <T> List<T> multipleSearchDataByQueryAndAggregation(String index, String type, Class<T> valueType, List<SearchSourceBuilder> searchSourceBuilders) {
        return esRepository.multipleSearchDataByQueryAndAggregation(index, type, valueType, searchSourceBuilders);
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
