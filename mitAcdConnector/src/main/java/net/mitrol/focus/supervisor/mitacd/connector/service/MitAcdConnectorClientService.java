package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClientListener;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;

/**
 * @author ladassus
 */
@Service
public class MitAcdConnectorClientService {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdConnectorClientService.class);

    private MitAcdConnectionInfo connectionInfo;
    @Autowired
    private MitAcdConnectorKafkaService kafkaService;

    @Autowired
    public MitAcdConnectorClientService(MitAcdConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    @PostConstruct
    public void init() {
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(this.connectionInfo.getEndPoint().getPort());
        } catch (IOException e) {
            logger.error(e);
        }
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e) {
                logger.error(e);
            }
            MitAcdClientListener client = new MitAcdClientListener();
            client.connect(socket);
        }
    }

    private class MitAcdClientListener implements SSupMitAcdClientListener {

        private SSupMitAcdClient ssupMitAcdClient;

        public void connect (Socket socket){
            ssupMitAcdClient = new SSupMitAcdClient(this, connectionInfo, Duration.ofMillis(500));
            ssupMitAcdClient.connect(socket);
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
        public void send(String message) {
            Validate.notNull(message, "message must be not null");
            kafkaService.sender(message);
        }
    }
}
