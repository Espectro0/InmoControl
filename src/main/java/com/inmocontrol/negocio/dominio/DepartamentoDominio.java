package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class DepartamentoDominio {
  private UUID id;
  private String nombre;
  private PaisDominio pais;

  private DepartamentoDominio(final Builder builder) {
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

  public PaisDominio getPais() {
    return pais;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setNombre(final String nombre) {
    this.nombre = UtilTexto.aplicarTrim(nombre);
  }

  private void setPais(final PaisDominio pais) {
    this.pais = pais;
  }

  public static class Builder {
    private UUID id;
    private String nombre;
    private PaisDominio pais;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder nombre(final String nombre) {
      this.nombre = nombre;
      return this;
    }

    public Builder pais(final PaisDominio pais) {
      this.pais = pais;
      return this;
    }

    public DepartamentoDominio build() {
      return new DepartamentoDominio(this);
    }
  }
}
