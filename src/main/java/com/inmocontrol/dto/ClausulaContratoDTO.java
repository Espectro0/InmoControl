package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilTexto;
import com.inmocontrol.transversal.UtilUUID;

import java.util.UUID;

public final class ClausulaContratoDTO {
  private UUID id;
  private AreaReferenciaDTO areaReferencia;
  private TipoAplicacionDTO tipoAplicacion;
  private String titulo;
  private String contenidoLegal;

  public ClausulaContratoDTO() {
	  setId(UtilUUID.UUID_CERO);
	  setAreaReferencia(new AreaReferenciaDTO());
	  setTipoAplicacion(new TipoAplicacionDTO());
	  setTitulo(UtilTexto.VACIO);
	  setContenidoLegal(UtilTexto.VACIO);
  }

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

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setAreaReferencia(final AreaReferenciaDTO areaReferencia) {
    this.areaReferencia = UtilObjeto.obtenerValorDefecto(areaReferencia, new AreaReferenciaDTO());
  }

  public void setTipoAplicacion(final TipoAplicacionDTO tipoAplicacion) {
    this.tipoAplicacion = UtilObjeto.obtenerValorDefecto(tipoAplicacion, new TipoAplicacionDTO());
  }

  public void setTitulo(final String titulo) {
    this.titulo = UtilTexto.aplicarTrim(titulo);
  }

  public void setContenidoLegal(final String contenidoLegal) {
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
