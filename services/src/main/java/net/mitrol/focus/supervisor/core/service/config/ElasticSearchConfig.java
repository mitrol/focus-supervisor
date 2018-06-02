package net.mitrol.focus.supervisor.core.service.config;

import java.net.InetAddress;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author ladassus
 */
@Configuration
@ComponentScan("net.mitrol.focus.supervisor.core.service.impl, net.mitrol.focus.supervisor.core.service.domain")
public class ElasticSearchConfig implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(ElasticSearchConfig.class);

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.pool}")
    private String poolSize;

    private TransportClient client;


    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error(e, "Error closing ElasticSearch client");
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Settings esSetting = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", true) // Increase sniffing mechanism to find ES cluster
                    .put("thread_pool.search.size", Integer.parseInt(poolSize)) // Increase the number of thread pools, temporarily set 5
                    .build();

            client = new PreBuiltTransportClient(esSetting);
            InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(host), Integer.valueOf(port));
            client.addTransportAddresses(inetSocketTransportAddress);

        } catch (Exception e) {
            logger.error(e, "Elasticsearch TransportClient create error!");
        }
    }
}
