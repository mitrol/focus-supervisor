package net.mitrol.supervisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mitrol.kafka.KafkaSender;

/**
 * 
 * @author ladassus
 *
 */
@RestController
public class DummyController {

  @Autowired
  private KafkaSender kafkaSender;
  
  @RequestMapping("/sendMessage")
  public void sendMessage(String message) {
    this.kafkaSender.send("dashboardUpdated", message);
  }
  
}
