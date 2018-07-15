package net.mitrol.focus.supervisor.core.service.config;

import net.mitrol.focus.supervisor.common.feign.FeignClientFactory;
import net.mitrol.focus.supervisor.core.service.CTApiClientService;
import net.mitrol.focus.supervisor.core.service.ESearchRESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;


/**
 * @author ladassus
 */
@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
public class ServiceConfig  {

    @Autowired
    private Environment environment;

    @Bean
    public FeignClientFactory<CTApiClientService> ctApiClientFactory() {
        return new FeignClientFactory("ct_api", CTApiClientService.class, environment);
    }

    @Bean
    public FeignClientFactory<ESearchRESTService> esServiceFactory() {
        return new FeignClientFactory("elasticsearch", ESearchRESTService.class, environment);
    }
}

