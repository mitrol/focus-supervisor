package net.mitrol.focus.supervisor.mitacd.connector.client;

import net.mitrol.acd.client.entities.MitAcdClientState;
import net.mitrol.acd.client.entities.MitAcdClientStatus;
import net.mitrol.acd.client.entities.MitAcdConnectionInfo;

@SuppressWarnings("WeakerAccess")
public class SSupMitAcdClientStatus extends MitAcdClientStatus {

    public SSupMitAcdClientStatus(MitAcdConnectionInfo mitAcdConnectionInfo, MitAcdClientState state) {
        super(mitAcdConnectionInfo.getEndPoint().getHost(), mitAcdConnectionInfo.getEndPoint().getPort(), state);
    }
}
