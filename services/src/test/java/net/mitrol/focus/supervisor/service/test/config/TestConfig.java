package net.mitrol.focus.supervisor.service.test.config;

import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import({ElasticSearchConfig.class})
@ComponentScan({
        "net.mitrol.focus.supervisor.core.service",
        "net.mitrol.focus.supervisor.core.service.domain"
})
@PropertySources({@PropertySource("classpath:application.properties")})
public class TestConfig {
}
