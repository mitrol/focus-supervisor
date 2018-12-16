package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClientListener;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ladassus
 */
@Service
public class SSupMitAcdClientListenerService implements SSupMitAcdClientListener {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(SSupMitAcdClientListenerService.class);

    private SSupMitAcdClient ssupMitAcdClient;

    @Autowired
    private MitAcdConnectorKafkaService kafkaService;

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
}
