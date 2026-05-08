package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class CiudadDominio {
    private UUID id;
    private String nombre;
    private DepartamentoDominio departamento;

    private CiudadDominio(final Builder builder) {
        setId(builder.id);
        setNombre(builder.nombre);
        setDepartamento(builder.departamento);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDominio getDepartamento() {
        return departamento;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    private void setDepartamento(final DepartamentoDominio departamento) {
        this.departamento = departamento;
    }

    public static class Builder {
        private UUID id;
        private String nombre;
        private DepartamentoDominio departamento;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder departamento(final DepartamentoDominio departamento) {
            this.departamento = departamento;
            return this;
        }

        public CiudadDominio build() {
            return new CiudadDominio(this);
        }
    }
}
