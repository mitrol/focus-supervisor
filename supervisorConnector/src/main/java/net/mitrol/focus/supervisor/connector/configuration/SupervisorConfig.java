package net.mitrol.focus.supervisor.connector.configuration;

import net.mitrol.focus.supervisor.common.feign.FeignClientFactory;
import net.mitrol.focus.supervisor.connector.service.CTApiClientService;
import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author ladassus
 */
@Configuration
@Import({ElasticSearchConfig.class})
@PropertySources({@PropertySource("classpath:application.properties")})
public class SupervisorConfig {

    @Autowired
    private Environment environment;

    @Bean
    public FeignClientFactory<CTApiClientService> ctApiClientFactory() {
        return new FeignClientFactory("ct_api", CTApiClientService.class, environment);
    }
}
