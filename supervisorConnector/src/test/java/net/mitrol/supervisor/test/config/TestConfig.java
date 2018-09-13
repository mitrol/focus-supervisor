package net.mitrol.supervisor.test.config;

import net.mitrol.focus.supervisor.connector.configuration.SupervisorConfig;
import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import({SupervisorConfig.class})
@PropertySources({@PropertySource("classpath:application.properties")})
public class TestConfig {
}
