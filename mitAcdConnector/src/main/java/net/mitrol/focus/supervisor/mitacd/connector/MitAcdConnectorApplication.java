package net.mitrol.focus.supervisor.mitacd.connector;

import net.mitrol.kafka.KafkaContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author ladassus.
 */
@SpringBootApplication
public class MitAcdConnectorApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(MitAcdConnectorApplication.class)
            .sources(new Class[] {KafkaContext.class}).run(args);
  }
}
