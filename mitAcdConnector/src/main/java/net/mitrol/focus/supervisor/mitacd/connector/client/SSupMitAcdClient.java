package net.mitrol.focus.supervisor.mitacd.connector.client;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.adapter.*;
import net.mitrol.utils.entities.SockMessage;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;

import java.time.Duration;

public class SSupMitAcdClient extends MitAcdClient<SSupMitAcdClientStatus, SSupMitAcdClientListener> {

    private MitrolLogger logger = MitrolLoggerImpl.getLogger(SSupMitAcdClient.class);

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

        switch (sockMessage.getCommand()) {
            case 9991: // registered
                mitAcdClientListener.onReady(this);
                fetch();
                break;
            case 1301: // datos de campaña de este intervalo (desde la última media hora hasta ahora)
                mitAcdClientListener.onCampaignIntervalStats(this, CampaignIntervalStatsAdapter.adapt(sockMessage));
                break;
            case 1303: // datos de campaña de hoy y la última media hora completa
                mitAcdClientListener.onCampaignDailyStats(this, CampaignDailyStatsAdapter.adapt(sockMessage));
                break;
            case 1311: // datos de lotes de este intervalo (desde la última media hora hasta ahora)
                mitAcdClientListener.onSplitIntervalStats(this, SplitIntervalStatsAdapter.adapt(sockMessage));
                break;
            case 1313: // datos de lotes de hoy y la última media hora completa
                mitAcdClientListener.onSplitDailyStats(this, SplitDailyStatsAdapter.adapt(sockMessage));
                break;
            case 1321: // datos de agente de este intervalo (desde la última media hora hasta ahora)
                mitAcdClientListener.onAgentIntervalStats(this, AgentIntervalStatsAdapter.adapt(sockMessage));
                break;
            case 1323: // datos de agente diario y última media hora completa
                mitAcdClientListener.onAgentDailyStats(this, AgentDailyStatsAdapter.adapt(sockMessage));
                break;
            case 1341: // datos de las interacciones en curso
                mitAcdClientListener.onInteractionsStats(this, InteractionStatsAdapter.adapt(sockMessage));
                break;
            case 1391: // batch finished
                mitAcdClientListener.onBatchFinished(this, sockMessage.getInteger("idInt"),
                        sockMessage.getDateTime("HoraActual", "MM/dd/yyyy HH:mm:ss.SSS"),
                        Duration.ofMinutes(sockMessage.getInteger("TiempoIntervalo")));
                new Thread(() -> {
                    try {
                        Thread.sleep(queryInterval.toMillis());
                        fetch();
                    } catch (InterruptedException e) {
                        logger.error(e, e.getMessage());
                    }
                }).start();
                break;
        }
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();

        SockMessage sockMessage = new SockMessage(9990);
        logger.debug(sockMessage.toString());
        this.send(sockMessage);
    }

    private void fetch() {
        SockMessage sockMessage = new SockMessage(1300);
        logger.debug(sockMessage.toString());
        this.send(sockMessage);
    }
}
