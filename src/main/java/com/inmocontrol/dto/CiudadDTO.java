package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class CiudadDTO {
  private UUID id;
  private String nombre;
  private DepartamentoDTO departamento;
  private UUID departamentoId;

  public CiudadDTO() {}

  private CiudadDTO(final Builder builder) {
    setId(builder.id);
    setNombre(builder.nombre);
    setDepartamento(builder.departamento);
    setDepartamentoId(builder.departamentoId);
  }

  public UUID getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public DepartamentoDTO getDepartamento() {
    return departamento;
  }

  public UUID getDepartamentoId() {
    return departamentoId;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setNombre(final String nombre) {
    this.nombre = UtilTexto.aplicarTrim(nombre);
  }

  public void setDepartamento(final DepartamentoDTO departamento) {
    this.departamento = departamento;
  }

  public void setDepartamentoId(final UUID departamentoId) {
    this.departamentoId = departamentoId;
  }

  public static class Builder {
    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;
    private UUID departamentoId;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder nombre(final String nombre) {
      this.nombre = nombre;
      return this;
    }

    public Builder departamento(final DepartamentoDTO departamento) {
      this.departamento = departamento;
      return this;
    }

    public Builder departamentoId(final UUID departamentoId) {
      this.departamentoId = departamentoId;
      return this;
    }

    public CiudadDTO build() {
      return new CiudadDTO(this);
    }
  }
}
