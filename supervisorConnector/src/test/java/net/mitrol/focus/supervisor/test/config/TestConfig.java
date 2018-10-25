package net.mitrol.focus.supervisor.test.config;

import net.mitrol.focus.supervisor.connector.configuration.SupervisorConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import({SupervisorConfig.class})
public class TestConfig {
}
