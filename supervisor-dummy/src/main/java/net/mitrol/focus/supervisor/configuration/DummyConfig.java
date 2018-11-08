package net.mitrol.focus.supervisor.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
/**
 * 
 * @author ladassus
 *
 */
@Configuration
@PropertySources({ @PropertySource("classpath:application.properties") })
public class DummyConfig {
}
