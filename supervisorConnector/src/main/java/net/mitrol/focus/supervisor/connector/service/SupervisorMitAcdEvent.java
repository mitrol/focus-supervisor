package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.mitct.mitacd.event.MitAcdEvent;

/**
 * @author ladassus
 */
public interface SupervisorMitAcdEvent {

    void processEvent (MitAcdEvent event);
}
