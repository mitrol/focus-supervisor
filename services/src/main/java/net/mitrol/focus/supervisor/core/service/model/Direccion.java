package net.mitrol.focus.supervisor.core.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Direccion implements Serializable {

    @JsonProperty
    private String id_direccion;

    @JsonProperty
    private String localidad;

    @JsonProperty
    private String calle;

    @JsonProperty
    private Integer numero;

    @JsonProperty
    private String codigoPostal;

    public Direccion() {
    }

    public String getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(String id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
