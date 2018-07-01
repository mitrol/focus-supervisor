package net.mitrol.focus.supervisor.core.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Vendedor implements Serializable {

    @JsonProperty
    private String id_vendedor;

    @JsonProperty
    private String name;

    @JsonProperty
    private String lastname;

    @JsonProperty
    private Direccion direccion;

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
}
