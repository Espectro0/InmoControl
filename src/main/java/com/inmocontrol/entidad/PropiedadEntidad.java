package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilNumero;
import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class PropiedadEntidad {
  private UUID id;
  private TipoPropiedadEntidad tipoPropiedad;
  private EstratoEntidad estrato;
  private String nombreInmueble;
  private String descripcionInmueble;
  private Integer areaMetros;
  private String direccion;
  private CiudadEntidad ciudad;

  private PropiedadEntidad(final Builder builder) {
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

  public TipoPropiedadEntidad getTipoPropiedad() {
    return tipoPropiedad;
  }

  public EstratoEntidad getEstrato() {
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

  public CiudadEntidad getCiudad() {
    return ciudad;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setTipoPropiedad(final TipoPropiedadEntidad tipoPropiedad) {
    this.tipoPropiedad = tipoPropiedad;
  }

  private void setEstrato(final EstratoEntidad estrato) {
    this.estrato = estrato;
  }

  private void setNombreInmueble(final String nombreInmueble) {
    this.nombreInmueble = UtilTexto.aplicarTrim(nombreInmueble);
  }

  private void setDescripcionInmueble(final String descripcionInmueble) {
    this.descripcionInmueble = UtilTexto.aplicarTrim(descripcionInmueble);
  }

  private void setAreaMetros(final Integer areaMetros) {
    this.areaMetros = UtilNumero.obtenerValorDefecto(areaMetros);
  }

  private void setDireccion(final String direccion) {
    this.direccion = UtilTexto.aplicarTrim(direccion);
  }

  private void setCiudad(final CiudadEntidad ciudad) {
    this.ciudad = ciudad;
  }

  public static class Builder {
    private UUID id;
    private TipoPropiedadEntidad tipoPropiedad;
    private EstratoEntidad estrato;
    private String nombreInmueble;
    private String descripcionInmueble;
    private Integer areaMetros;
    private String direccion;
    private CiudadEntidad ciudad;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder tipoPropiedad(final TipoPropiedadEntidad tipoPropiedad) {
      this.tipoPropiedad = tipoPropiedad;
      return this;
    }

    public Builder estrato(final EstratoEntidad estrato) {
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

    public Builder ciudad(final CiudadEntidad ciudad) {
      this.ciudad = ciudad;
      return this;
    }

    public PropiedadEntidad build() {
      return new PropiedadEntidad(this);
    }
  }
}
