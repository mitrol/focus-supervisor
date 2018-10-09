package net.mitrol.focus.supervisor.connector.service;

import net.mitrol.focus.supervisor.common.event.EventFilter;
import net.mitrol.focus.supervisor.common.event.EventRequest;

/**
 * @author ladassus
 */
public interface SupervisorEvent {

    void processEvent (EventRequest event);

    void subscribeEvent (EventRequest event);

    void unsubscribeEvent (EventRequest event);

    void changeEventFilter (EventRequest event, EventFilter filter);

    void pauseEvent (EventRequest event);

    void playEvent (EventRequest event);
}
