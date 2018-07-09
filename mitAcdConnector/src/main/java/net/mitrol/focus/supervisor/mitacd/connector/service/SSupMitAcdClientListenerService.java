package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClientListener;
import net.mitrol.focus.supervisor.models.*;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ladassus
 */
@Service
public class SSupMitAcdClientListenerService implements SSupMitAcdClientListener {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(SSupMitAcdClientListenerService.class);

    private SSupMitAcdClient ssupMitAcdClient;
    private List<Object> listMessageMitACD = new ArrayList<>();

    @Autowired
    private MitAcdKafkaService kafkaService;

    @Autowired
    public SSupMitAcdClientListenerService(MitAcdConnectionInfo connectionInfo) {
        ssupMitAcdClient = new SSupMitAcdClient(this, connectionInfo, Duration.ofMillis(500));
        ssupMitAcdClient.connect();
    }

    public SSupMitAcdClient getSsupMitAcdClient() {
        return ssupMitAcdClient;
    }


    @Override
    public String getClientIdentifier(MitAcdClient mitAcdClient) {
        return "ssup";
    }

    @Override
    public void onConnected(MitAcdClient mitAcdClient) {
        ssupMitAcdClient.start();
    }

    @Override
    public void onClosed(MitAcdClient mitAcdClient, DisconnectedReason disconnectedReason) {
    }

    @Override
    public void onReady(SSupMitAcdClient ssupMitAcdClient) {
    }

    @Override
    public void onBatchFinished(SSupMitAcdClient ssupMitAcdClient,
                                Integer intervalId, Instant serverDateTime, Duration intervalDuration) {
        String json = JsonMapper.getInstance().getStringJsonFromObject(listMessageMitACD);
        logger.debug("Sending messages to kafka -> " + json);
        kafkaService.sender(json);
        listMessageMitACD.clear();
    }

    @Override
    public void onCampaignIntervalStats(SSupMitAcdClient ssupMitAcdClient,
                                        CampaignIntervalStats campaignIntervalStats) {
        listMessageMitACD.add(campaignIntervalStats);
    }

    @Override
    public void onCampaignDailyStats(SSupMitAcdClient ssupMitAcdClient, CampaignDailyStats campaignDailyStats) {
        listMessageMitACD.add(campaignDailyStats);
    }

    @Override
    public void onSplitIntervalStats(SSupMitAcdClient sSupMitAcdClient, SplitIntervalStats splitIntervalStats) {
        listMessageMitACD.add(splitIntervalStats);
    }

    @Override
    public void onSplitDailyStats(SSupMitAcdClient ssupMitAcdClient, SplitDailyStats SplitDailyStats) {
        listMessageMitACD.add(SplitDailyStats);
    }

    @Override
    public void onAgentIntervalStats(SSupMitAcdClient ssupMitAcdClient, AgentIntervalStats agentIntervalStats) {
        listMessageMitACD.add(agentIntervalStats);
    }

    @Override
    public void onAgentDailyStats(SSupMitAcdClient ssupMitAcdClient, AgentDailyStats agentDailyStats) {
        listMessageMitACD.add(agentDailyStats);
    }

    @Override
    public void onInteractionsStats(SSupMitAcdClient ssupMitAcdClient, InteractionStats interactionStats) {
        listMessageMitACD.add(interactionStats);
    }
}
