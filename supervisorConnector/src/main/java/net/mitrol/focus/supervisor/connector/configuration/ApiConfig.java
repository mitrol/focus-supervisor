package net.mitrol.focus.supervisor.connector.configuration;

import net.mitrol.focus.supervisor.connector.api.client.CTApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import net.mitrol.focus.supervisor.common.feign.FeignClientFactory;

/**
 * 
 * @author ladassus
 *
 */
@Configuration
@PropertySources({ @PropertySource("classpath:application.properties") })
public class ApiConfig {
  
  @Autowired
  private Environment environment;
  
  @Bean
  public FeignClientFactory<CTApiClient> ctApiClientFactory() {
    return new FeignClientFactory("ct_api", CTApiClient.class, environment);
  }
}
