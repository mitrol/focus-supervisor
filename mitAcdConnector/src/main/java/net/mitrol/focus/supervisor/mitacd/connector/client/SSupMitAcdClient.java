package net.mitrol.focus.supervisor.mitacd.connector.client;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.acd.client.util.SockMessage;
import net.mitrol.utils.ExecutorBuilder;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author ladassus
 */
public class SSupMitAcdClient extends MitAcdClient<SSupMitAcdClientStatus, SSupMitAcdClientListener> {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SSupMitAcdClient.class);

    private ScheduledExecutorService scheduler = ExecutorBuilder.buildNewSingleScheduledExecutorService("SSupMitAcdExecutor");
    private SSupMitAcdClientStatus status;
    private Duration queryInterval;

    @SuppressWarnings("WeakerAccess")
    public SSupMitAcdClient(SSupMitAcdClientListener ssupMitAcdClientListener, MitAcdConnectionInfo connectionInfo,
                            Duration queryInterval) {
        super(ssupMitAcdClientListener, connectionInfo);
        status = new SSupMitAcdClientStatus(connectionInfo, getMitAcdClientState());
        this.queryInterval = queryInterval;
    }

    // region
    @Override
    public SSupMitAcdClientStatus getStatus() {
        return status;
    }

    @Override
    public String getGroup() {
        return "MitACD";
    }

    @Override
    public String getIdentifier() {
        return "mitacd-kafka-connector";
    }

    @Override
    public String getDescription() {
        return "MitACD Kafka Connector";
    }
    // endregion

    @Override
    protected void onMitAcdMessageReceived(SockMessage sockMessage) {
        logger.debug(sockMessage.toString());
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();

       /* SockMessage sockMessage = new SockMessage(9990);
        logger.debug(sockMessage.toString());
        this.send(sockMessage);*/
    }

   private void fetch() {
        /*SockMessage sockMessage = new SockMessage(1300);
        logger.debug(sockMessage.toString());
        this.send(sockMessage);*/
    }
}
