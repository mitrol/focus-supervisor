package net.mitrol.focus.supervisor.mitct.mitacd.event;

import net.mitrol.ct.api.enums.Channel;
import net.mitrol.ct.api.enums.InteractionState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class EstadosInteraccionesTest {

    private List<InteractionEvent> events;

    private void setUpCallAnswered(Instant timestamp) {
        // eventos de una interacción que hizo un agente y fue atendida
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        String interactionId = String.format("%02d%02d%02d%02d%02d%02d%03d_ACD_00000",
                ldt.getYear() % 20, ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getNano() / 1000 / 1000);

        events.add(new InteractionEvent(
                Date.from(timestamp), interactionId, 1, InteractionState.DialingAgente, 1, 1, 1, null, null, null, null, Channel.Llamada, null, null));

        events.add(new InteractionEvent(
                Date.from(timestamp.plusSeconds(60)), interactionId, 1, InteractionState.Ringing, 1, 1, 1, null, null, null, null, Channel.Llamada, null, null));

        events.add(new InteractionEvent(
                Date.from(timestamp.plusSeconds(120)), interactionId, 1, InteractionState.Talking, 1, 1, 1, null, null, null, null, Channel.Llamada, null, null));
    }

    @Before
    public void setUp() {

        events = new ArrayList<>();
        setUpCallAnswered(Instant.now().minusSeconds(60));
        setUpCallAnswered(Instant.now().minusSeconds(61));
        setUpCallAnswered(Instant.now().minusSeconds(59));

    }

    /**
     * devuelve cantidad de interacciones por estado, excepto terminadas
     */
    @Test
    public void getEstadosInteracciones() {

        // busacr los últimos eventos por cada interacción
        Map<String, InteractionEvent> maxInteractions = new HashMap<>();
        for (InteractionEvent event : events) {
            if (!maxInteractions.containsKey(event.getInteractionId())) {
                if (event.getState() != InteractionState.Idle)
                    maxInteractions.put(event.getInteractionId(), event);
                continue;
            }

            InteractionEvent oldEvent = maxInteractions.get(event.getInteractionId());
            if (event.getTimestamp().compareTo(oldEvent.getTimestamp()) > 0)
                maxInteractions.replace(event.getInteractionId(), event);
        }

        // agrupo por estado
        Map<InteractionState, Integer> estadosInteracciones = new HashMap<>();
        for (String interactionId : maxInteractions.keySet()) {
            InteractionEvent event = maxInteractions.get(interactionId);
            if(event.getState() == InteractionState.Idle)
                continue;

            if (!estadosInteracciones.containsKey(event.getState()))
                estadosInteracciones.put(event.getState(), 0);

            estadosInteracciones.replace(event.getState(), estadosInteracciones.get(event.getState()) + 1);
        }

        Assert.assertEquals(3, estadosInteracciones.get(InteractionState.Talking).intValue());
    }
}
