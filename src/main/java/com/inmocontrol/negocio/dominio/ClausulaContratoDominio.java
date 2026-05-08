package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ClausulaContratoDominio {
    private UUID id;
    private AreaReferenciaDominio areaReferencia;
    private TipoAplicacionDominio tipoAplicacion;
    private String titulo;
    private String contenidoLegal;

    private ClausulaContratoDominio(final Builder builder) {
        setId(builder.id);
        setAreaReferencia(builder.areaReferencia);
        setTipoAplicacion(builder.tipoAplicacion);
        setTitulo(builder.titulo);
        setContenidoLegal(builder.contenidoLegal);
    }

    public UUID getId() {
        return id;
    }

    public AreaReferenciaDominio getAreaReferencia() {
        return areaReferencia;
    }

    public TipoAplicacionDominio getTipoAplicacion() {
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

    private void setAreaReferencia(final AreaReferenciaDominio areaReferencia) {
        this.areaReferencia = areaReferencia;
    }

    private void setTipoAplicacion(final TipoAplicacionDominio tipoAplicacion) {
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
        private AreaReferenciaDominio areaReferencia;
        private TipoAplicacionDominio tipoAplicacion;
        private String titulo;
        private String contenidoLegal;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder areaReferencia(final AreaReferenciaDominio areaReferencia) {
            this.areaReferencia = areaReferencia;
            return this;
        }

        public Builder tipoAplicacion(final TipoAplicacionDominio tipoAplicacion) {
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

        public ClausulaContratoDominio build() {
            return new ClausulaContratoDominio(this);
        }
    }
}
