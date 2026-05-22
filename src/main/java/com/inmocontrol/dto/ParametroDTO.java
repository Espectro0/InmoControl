package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ParametroDTO {
  private UUID id;
  private String placeholder;
  private String descripcion;

  public ParametroDTO() {}

  private ParametroDTO(final Builder builder) {
    setId(builder.id);
    setPlaceholder(builder.placeholder);
    setDescripcion(builder.descripcion);
  }

  public UUID getId() {
    return id;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public String getDescripcion() {
    return descripcion;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  public void setPlaceholder(final String placeholder) {
    this.placeholder = UtilTexto.aplicarTrim(placeholder);
  }

  public void setDescripcion(final String descripcion) {
    this.descripcion = UtilTexto.aplicarTrim(descripcion);
  }

  public static class Builder {
    private UUID id;
    private String placeholder;
    private String descripcion;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder placeholder(final String placeholder) {
      this.placeholder = placeholder;
      return this;
    }

    public Builder descripcion(final String descripcion) {
      this.descripcion = descripcion;
      return this;
    }

    public ParametroDTO build() {
      return new ParametroDTO(this);
    }
  }
}
