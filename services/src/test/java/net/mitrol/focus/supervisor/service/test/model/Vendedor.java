package net.mitrol.focus.supervisor.service.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import net.mitrol.ct.api.enums.AgentState;
import net.mitrol.focus.supervisor.service.test.model.jacksonn.VendedorDeserializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;

public class Vendedor implements Serializable {

    private String id_vendedor;

    private String name;

    private String lastname;

    private Direccion direccion;

    private String date;

    /*
    * TODO Eliminar una vez que se trabaje con los datos reales.
    * */
    @JsonDeserialize(using = VendedorDeserializer.class)
    @JsonProperty
    ////@JsonIgnore
    private Map<AgentState, Duration> agentStateDurations;

    public Vendedor() {
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<AgentState, Duration> getAgentStateDurations() {
        return agentStateDurations;
    }

    public void setAgentStateDurations(Map<AgentState, Duration> agentStateDurations) {
        this.agentStateDurations = agentStateDurations;
    }
}
