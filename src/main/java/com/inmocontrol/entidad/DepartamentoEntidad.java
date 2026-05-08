package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class DepartamentoEntidad {
    private UUID id;
    private String nombre;
    private PaisEntidad pais;

    private DepartamentoEntidad(final Builder builder) {
        setId(builder.id);
        setNombre(builder.nombre);
        setPais(builder.pais);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisEntidad getPais() {
        return pais;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    private void setPais(final PaisEntidad pais) {
        this.pais = pais;
    }

    public static class Builder {
        private UUID id;
        private String nombre;
        private PaisEntidad pais;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder pais(final PaisEntidad pais) {
            this.pais = pais;
            return this;
        }

        public DepartamentoEntidad build() {
            return new DepartamentoEntidad(this);
        }
    }
}
