package net.mitrol.focus.supervisor.connector.configuration;

import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import net.mitrol.focus.supervisor.core.service.config.ServiceConfig;
import org.springframework.context.annotation.*;

/**
 * @author ladassus
 */
@Configuration
@Import({ServiceConfig.class, ElasticSearchConfig.class})
@ComponentScan("net.mitrol.focus.supervisor.connector.service, net.mitrol.focus.supervisor.core.service")
@PropertySources({@PropertySource("classpath:application.properties")})
public class SupervisorConfig {
}
