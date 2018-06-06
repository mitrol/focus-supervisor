package net.mitrol.focus.supervisor.core.service.config;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
public class ElasticSearchConfig implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    private static MitrolLogger log = MitrolLoggerImpl.getLogger(ElasticSearchConfig.class);

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    @Value("${mitrol.http.client.host}")
    private String host;
    @Value("${mitrol.http.client.port}")
    private String port;
    @Value("${mitrol.http.client.sheme}")
    private String sheme;


    private RestHighLevelClient restHighLevelClient;

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            log.error(e, "Error closing ElasticSearch client: ");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, Integer.valueOf(port), sheme)));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
