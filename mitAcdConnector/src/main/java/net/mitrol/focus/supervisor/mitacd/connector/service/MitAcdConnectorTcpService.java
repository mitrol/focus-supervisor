package net.mitrol.focus.supervisor.mitacd.connector.service;

import net.mitrol.focus.supervisor.mitacd.connector.tcp.MitAcdConnectorServer;
import net.mitrol.focus.supervisor.mitacd.connector.tcp.SockMessage;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


/**
 * @author ladassus
 */
@Service
public class MitAcdConnectorTcpService extends MitAcdConnectorServer {

    private static MitrolLogger logger = MitrolLoggerImpl.getLogger(MitAcdConnectorTcpService.class);

    @Value("${mitacd.conn.port}")
    private int port;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        super.init(port);
    }

    @Override
    public void onMitAcdMessageReceived(SockMessage sockMessage) {
        //TODO
        logger.info((String) sockMessage.getType(), "     ", (String) sockMessage.getPayload());

    }
}
