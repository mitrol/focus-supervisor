package net.mitrol.focus.supervisor.connector;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import net.mitrol.kafka.KafkaContext;

/**
 * @author ladassus.
 */
@SpringBootApplication
public class SupervisorApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(SupervisorApplication.class)
        .sources(new Class[] {KafkaContext.class}).run(args);
  }
}
