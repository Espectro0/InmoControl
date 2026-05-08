package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ClausulaContratoDTO {
    private UUID id;
    private AreaReferenciaDTO areaReferencia;
    private TipoAplicacionDTO tipoAplicacion;
    private String titulo;
    private String contenidoLegal;

    private ClausulaContratoDTO(final Builder builder) {
        setId(builder.id);
        setAreaReferencia(builder.areaReferencia);
        setTipoAplicacion(builder.tipoAplicacion);
        setTitulo(builder.titulo);
        setContenidoLegal(builder.contenidoLegal);
    }

    public UUID getId() {
        return id;
    }

    public AreaReferenciaDTO getAreaReferencia() {
        return areaReferencia;
    }

    public TipoAplicacionDTO getTipoAplicacion() {
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

    private void setAreaReferencia(final AreaReferenciaDTO areaReferencia) {
        this.areaReferencia = areaReferencia;
    }

    private void setTipoAplicacion(final TipoAplicacionDTO tipoAplicacion) {
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
        private AreaReferenciaDTO areaReferencia;
        private TipoAplicacionDTO tipoAplicacion;
        private String titulo;
        private String contenidoLegal;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder areaReferencia(final AreaReferenciaDTO areaReferencia) {
            this.areaReferencia = areaReferencia;
            return this;
        }

        public Builder tipoAplicacion(final TipoAplicacionDTO tipoAplicacion) {
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

        public ClausulaContratoDTO build() {
            return new ClausulaContratoDTO(this);
        }
    }
}
