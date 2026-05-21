package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class AreaReferenciaEntidad {
  private UUID id;
  private String nombre;

  private AreaReferenciaEntidad(final Builder builder) {
    setId(builder.id);
    setNombre(builder.nombre);
  }

  public UUID getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setNombre(final String nombre) {
    this.nombre = UtilTexto.aplicarTrim(nombre);
  }

  public static class Builder {
    private UUID id;
    private String nombre;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder nombre(final String nombre) {
      this.nombre = nombre;
      return this;
    }

    public AreaReferenciaEntidad build() {
      return new AreaReferenciaEntidad(this);
    }
  }
}
