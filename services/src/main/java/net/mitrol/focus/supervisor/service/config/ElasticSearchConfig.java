package net.mitrol.focus.supervisor.service.config;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ladassus
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "net.mitrol.focus.supervisor.service.repository")
public class ElasticSearchConfig {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(ElasticSearchConfig.class);

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.clustername}")
    private String clusterName;

    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public Client client() {
        Settings esSetting = Settings.builder().put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .put("thread_pool.search.size", Integer.parseInt(poolSize))
                .build();
        try {
            TransportClient client= new PreBuiltTransportClient(esSetting).addTransportAddress( new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            logger.error(e, "Error building Elasticsearch client");
        }
        return client();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
