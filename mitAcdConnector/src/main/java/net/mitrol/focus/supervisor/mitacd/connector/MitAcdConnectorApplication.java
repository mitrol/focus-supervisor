package net.mitrol.focus.supervisor.mitacd.connector;

import net.mitrol.focus.supervisor.common.CommonApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.mitrol.kafka.KafkaContext;

/**
 * @author ladassus.
 */
@SpringBootApplication
public class MitAcdConnectorApplication {

  public static void main(String[] args) {
    SpringApplication.run(MitAcdConnectorApplication.class, args);
  }
}
