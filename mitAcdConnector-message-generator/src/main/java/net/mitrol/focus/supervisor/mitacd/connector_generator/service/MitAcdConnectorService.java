package net.mitrol.focus.supervisor.mitacd.connector_generator.service;

import feign.RequestLine;
import org.springframework.stereotype.Service;

@Service
public interface MitAcdConnectorService {

    @RequestLine("POST /webhook")
    void postEvent(String event);

}
