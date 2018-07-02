package net.mitrol.focus.supervisor.connector.service;

import javax.annotation.PostConstruct;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mitrol.kafka.KafkaReceiver;
import net.mitrol.kafka.KafkaReceiverListener;

/**
 * 
 * @author ladassus
 *
 */
@Service
public class KafkaReceiverService {

  private static MitrolLogger logger = MitrolLoggerImpl.getLogger(KafkaReceiverService.class);

  @Autowired
  private KafkaReceiver receiver;

  @PostConstruct
  public void init() {
    this.receiver.registerListener("supervisor", new KafkaReceiverListener<String>() {
      @Override
      public void processMessage(String source, String topic, String value) {
        logger.info("processMessage " + source + " " + topic + " " + value);
      }
    });
  }
}
