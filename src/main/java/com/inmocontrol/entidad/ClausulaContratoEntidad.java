package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ClausulaContratoEntidad {
    private UUID id;
    private AreaReferenciaEntidad areaReferencia;
    private TipoAplicacionEntidad tipoAplicacion;
    private String titulo;
    private String contenidoLegal;

    private ClausulaContratoEntidad(final Builder builder) {
        setId(builder.id);
        setAreaReferencia(builder.areaReferencia);
        setTipoAplicacion(builder.tipoAplicacion);
        setTitulo(builder.titulo);
        setContenidoLegal(builder.contenidoLegal);
    }

    public UUID getId() {
        return id;
    }

    public AreaReferenciaEntidad getAreaReferencia() {
        return areaReferencia;
    }

    public TipoAplicacionEntidad getTipoAplicacion() {
        return tipoAplicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenidoLegal() {
        return contenidoLegal;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setAreaReferencia(final AreaReferenciaEntidad areaReferencia) {
        this.areaReferencia = areaReferencia;
    }

    private void setTipoAplicacion(final TipoAplicacionEntidad tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    private void setTitulo(final String titulo) {
        this.titulo = UtilTexto.aplicarTrim(titulo);
    }

    private void setContenidoLegal(final String contenidoLegal) {
        this.contenidoLegal = UtilTexto.aplicarTrim(contenidoLegal);
    }

    public static class Builder {
        private UUID id;
        private AreaReferenciaEntidad areaReferencia;
        private TipoAplicacionEntidad tipoAplicacion;
        private String titulo;
        private String contenidoLegal;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder areaReferencia(final AreaReferenciaEntidad areaReferencia) {
            this.areaReferencia = areaReferencia;
            return this;
        }

        public Builder tipoAplicacion(final TipoAplicacionEntidad tipoAplicacion) {
            this.tipoAplicacion = tipoAplicacion;
            return this;
        }

        public Builder titulo(final String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder contenidoLegal(final String contenidoLegal) {
            this.contenidoLegal = contenidoLegal;
            return this;
        }

        public ClausulaContratoEntidad build() {
            return new ClausulaContratoEntidad(this);
        }
    }
}
