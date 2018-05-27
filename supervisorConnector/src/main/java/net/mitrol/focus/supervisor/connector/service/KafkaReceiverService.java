package net.mitrol.focus.supervisor.connector.service;

import java.util.List;
import javax.annotation.PostConstruct;

import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import net.mitrol.ct.api.controllers.responses.AgentProfileResponse;
import net.mitrol.ct.api.controllers.responses.CampaignResponse;
import net.mitrol.ct.api.controllers.responses.ListResponse;
import net.mitrol.ct.api.entities.Group;
import net.mitrol.focus.supervisor.connector.api.client.CTApiClient;
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
  @Autowired
  private CTApiClient ctApiClient;

  @PostConstruct
  public void init() {
    this.receiver.registerListener("supervisor", new KafkaReceiverListener<String>() {
      @Override
      public void processMessage(String source, String topic, String value) {
        logger.info("processMessage " + source + " " + topic + " " + value);
        /*
        * This part is only to Test.
        * */
        List<CampaignResponse> campaignResponseList = ctApiClient.getCampaigns();
        CampaignResponse campaignResponse = ctApiClient.getCampaignById(Long.valueOf(20));
        List<AgentProfileResponse> agentProfileResponse = ctApiClient.getAgentProfilesById(Long.valueOf(3));
        List<ListResponse> listResponse = ctApiClient.getLotesById(Long.valueOf(5));
        Group group = ctApiClient.getGroupById(Long.valueOf(5));
        logger.info("Testing");
      }
    });
  }
}
