package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.mitct.mitacd.event.MitAcdEvent;

/**
 * @author ladassus
 */
public interface SupervisorMitAcdEvent {

    void processEvent (MitAcdEvent event);
}
