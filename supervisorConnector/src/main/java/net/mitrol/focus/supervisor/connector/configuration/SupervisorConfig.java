package net.mitrol.focus.supervisor.connector.configuration;

import net.mitrol.focus.supervisor.common.feign.FeignClientFactory;
import net.mitrol.focus.supervisor.core.service.CTApiClientService;
import net.mitrol.focus.supervisor.core.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author ladassus
 */
@Configuration
@Import({ServiceConfig.class})
@PropertySources({@PropertySource("classpath:application.properties")})
public class SupervisorConfig {
}
