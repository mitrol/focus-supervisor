package net.mitrol.supervisor.test.config;

import net.mitrol.focus.supervisor.connector.configuration.SupervisorConfig;
import net.mitrol.focus.supervisor.core.service.config.ElasticSearchConfig;
import org.springframework.context.annotation.*;
import org.springframework.test.context.TestPropertySource;

@Configuration
@Import({SupervisorConfig.class})
public class TestConfig {
}
