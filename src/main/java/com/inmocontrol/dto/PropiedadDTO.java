package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilNumero;
import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class PropiedadDTO {
  private UUID id;
  private TipoPropiedadDTO tipoPropiedad;
  private EstratoDTO estrato;
  private String nombreInmueble;
  private String descripcionInmueble;
  private Integer areaMetros;
  private String direccion;
  private CiudadDTO ciudad;

  public PropiedadDTO() {}

  private PropiedadDTO(final Builder builder) {
    setId(builder.id);
    setTipoPropiedad(builder.tipoPropiedad);
    setEstrato(builder.estrato);
    setNombreInmueble(builder.nombreInmueble);
    setDescripcionInmueble(builder.descripcionInmueble);
    setAreaMetros(builder.areaMetros);
    setDireccion(builder.direccion);
    setCiudad(builder.ciudad);
  }

  public UUID getId() {
    return id;
  }

  public TipoPropiedadDTO getTipoPropiedad() {
    return tipoPropiedad;
  }

  public EstratoDTO getEstrato() {
    return estrato;
  }

  public String getNombreInmueble() {
    return nombreInmueble;
  }

  public String getDescripcionInmueble() {
    return descripcionInmueble;
  }

  public Integer getAreaMetros() {
    return areaMetros;
  }

  public String getDireccion() {
    return direccion;
  }

  public CiudadDTO getCiudad() {
    return ciudad;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setTipoPropiedad(final TipoPropiedadDTO tipoPropiedad) {
    this.tipoPropiedad = tipoPropiedad;
  }

  public void setEstrato(final EstratoDTO estrato) {
    this.estrato = estrato;
  }

  public void setNombreInmueble(final String nombreInmueble) {
    this.nombreInmueble = UtilTexto.aplicarTrim(nombreInmueble);
  }

  public void setDescripcionInmueble(final String descripcionInmueble) {
    this.descripcionInmueble = UtilTexto.aplicarTrim(descripcionInmueble);
  }

  public void setAreaMetros(final Integer areaMetros) {
    this.areaMetros = UtilNumero.obtenerValorDefecto(areaMetros);
  }

  public void setDireccion(final String direccion) {
    this.direccion = UtilTexto.aplicarTrim(direccion);
  }

  public void setCiudad(final CiudadDTO ciudad) {
    this.ciudad = ciudad;
  }

  public static class Builder {
    private UUID id;
    private TipoPropiedadDTO tipoPropiedad;
    private EstratoDTO estrato;
    private String nombreInmueble;
    private String descripcionInmueble;
    private Integer areaMetros;
    private String direccion;
    private CiudadDTO ciudad;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder tipoPropiedad(final TipoPropiedadDTO tipoPropiedad) {
      this.tipoPropiedad = tipoPropiedad;
      return this;
    }

    public Builder estrato(final EstratoDTO estrato) {
      this.estrato = estrato;
      return this;
    }

    public Builder nombreInmueble(final String nombreInmueble) {
      this.nombreInmueble = nombreInmueble;
      return this;
    }

    public Builder descripcionInmueble(final String descripcionInmueble) {
      this.descripcionInmueble = descripcionInmueble;
      return this;
    }

    public Builder areaMetros(final Integer areaMetros) {
      this.areaMetros = areaMetros;
      return this;
    }

    public Builder direccion(final String direccion) {
      this.direccion = direccion;
      return this;
    }

    public Builder ciudad(final CiudadDTO ciudad) {
      this.ciudad = ciudad;
      return this;
    }

    public PropiedadDTO build() {
      return new PropiedadDTO(this);
    }
  }
}
