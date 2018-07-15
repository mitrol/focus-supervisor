package net.mitrol.focus.supervisor.core.service;

import feign.Body;
import feign.Headers;
import feign.RequestLine;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.get.GetResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ESearchRESTService {

    @RequestLine("GET /.kibana/_search")
    public Map<String, Object> getESKibanaInfo ();
}
