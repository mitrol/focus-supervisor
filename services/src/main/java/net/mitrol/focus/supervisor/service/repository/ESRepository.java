package net.mitrol.focus.supervisor.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface ESRepository<T> extends ElasticsearchRepository <T, Long> {

    Optional<T> findById(Long id);
}
