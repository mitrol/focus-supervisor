package net.mitrol.focus.supervisor.mitacd.connector.client;

import net.mitrol.acd.client.interfaces.MitAcdClientListener;

public interface SSupMitAcdClientListener extends MitAcdClientListener {

    void onReady(SSupMitAcdClient ssupMitAcdClient);
}
