package net.mitrol.supervisor;

import net.mitrol.supervisor.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import net.mitrol.kafka.KafkaContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Supervisor application.
 * 
 * @author ladassus.
 */
@SpringBootApplication
public class DummyApplication implements CommandLineRunner {

  public static void main(String[] args){
    new SpringApplicationBuilder(DummyApplication.class)
        .sources(new Class[] {KafkaContext.class}).run(args);
  }

  @Autowired
  private KafkaSenderService sender;

  @Override
  public void run(String... args) throws Exception {
    do {
      int preview = ThreadLocalRandom.current().nextInt(0,  10);
      int dial = ThreadLocalRandom.current().nextInt(0,  10);
      int ring = ThreadLocalRandom.current().nextInt(0,  10);
      int talking = ThreadLocalRandom.current().nextInt(0,  10);
      int hold = ThreadLocalRandom.current().nextInt(0,  10);
      int acw = ThreadLocalRandom.current().nextInt(0,  10);
      String msg1 = "{\n" +
              "\"dashboardId\": 1,\n" +
              "\"updatedWidgets\": [{\n" +
              "\n" +
              "\"widgetId\": 1,\n" +
              "\"values\": [{\n" +
              "\"name\": \"Preview\",\n" +
              "\"value\":" + preview + "\n" +
              "},\n" +
              "{\n" +
              "\"name\": \"Dial\",\n" +
              "\"value\":" + dial + "\n" +
              "},\n" +
              "{\n" +
              "\"name\": \"Ring\",\n" +
              "\"value\":" + ring + "\n" +
              "},\n" +
              "{\n" +
              "\"name\": \"Talking\",\n" +
              "\"value\":" + talking + "\n" +
              "},\n" +
              "{\n" +
              "\"name\": \"Hold\",\n" +
              "\"value\":" + hold + "\n" +
              "},\n" +
              "{\n" +
              "\"name\": \"ACW\",\n" +
              "\"value\":" + acw + "\n" +
              "}\n" +
              "]\n" +
              "}]\n" +
              "}";
      int value = ThreadLocalRandom.current().nextInt(0,  10);
      String msg2 = "{\"supervisor\":{\"value\":" + value + "}}";

      sender.sender("supervisor2",msg2);
      sender.sender("dashboardUpdated2",msg1);

      Thread.sleep(5000);
    } while (true);
  }
}
