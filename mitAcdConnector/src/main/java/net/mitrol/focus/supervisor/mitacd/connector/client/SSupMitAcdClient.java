package net.mitrol.focus.supervisor.mitacd.connector.client;

import com.google.gson.reflect.TypeToken;
import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.acd.client.util.SockMessage;
import net.mitrol.focus.supervisor.mitct.mitacd.event.RegisterEvent;
import net.mitrol.utils.json.JsonMapper;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Map;

/**
 * @author ladassus
 */
public class SSupMitAcdClient extends MitAcdClient<SSupMitAcdClientStatus, SSupMitAcdClientListener> {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(SSupMitAcdClient.class);

    private SSupMitAcdClientListener ssupMitAcdClientListener;
    private SSupMitAcdClientStatus status;
    private Duration queryInterval;
    private Type type = new TypeToken<Map<String, Object>>(){}.getType();

    @SuppressWarnings("WeakerAccess")
    public SSupMitAcdClient(SSupMitAcdClientListener ssupMitAcdClientListener, MitAcdConnectionInfo connectionInfo,
                            Duration queryInterval) {
        super(ssupMitAcdClientListener, connectionInfo);
        this.ssupMitAcdClientListener = ssupMitAcdClientListener;
        this.status = new SSupMitAcdClientStatus(connectionInfo, getMitAcdClientState());
        this.queryInterval = queryInterval;
    }

    // region
    @Override
    public SSupMitAcdClientStatus getStatus() {
        return status;
    }

    @Override
    public String getGroup() {
        return "MitAcd";
    }

    @Override
    public String getIdentifier() {
        return "mitacd-kafka-connector";
    }

    @Override
    public String getDescription() {
        return "MitAcd Kafka Connector";
    }
    // endregion

    @Override
    protected void onMitAcdMessageReceived(SockMessage sockMessage) {
        logger.debug(sockMessage.toString());
        String message = sockMessage.getsMessage();
        try {
            Map<String, Object> map = JsonMapper.getInstance().getObjectFromString(message, type);
            if (map.containsKey(RegisterEvent.TYPE)){;
                register();
            }
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
    }

    private void register (){
        SockMessage sockMessage = new SockMessage();
        sockMessage.setsMessage("{\"register_event_response\":{\"charset\": \"UTF-8\"}}");
        this.send(sockMessage);
    }
}
