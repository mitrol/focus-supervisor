package net.mitrol.focus.supervisor.service;

import net.mitrol.focus.supervisor.service.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ladassus
 */
@Service
public class ElasticSearchService {

    @Autowired
    ESRepository esRepository;

}
