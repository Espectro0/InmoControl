package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilDate;
import com.inmocontrol.transversal.UtilNumero;
import com.inmocontrol.transversal.UtilTexto;
import java.util.Date;
import java.util.UUID;

public final class PersonaDTO {
  private UUID id;
  private TipoDocumentoDTO tipoDocumento;
  private String numeroIdentificacion;
  private String primerNombre;
  private String segundoNombre;
  private String primerApellido;
  private String segundoApellido;
  private String numeroTelefonico;
  private String correoElectronico;
  private String direccionResidencia;
  private CiudadDTO ciudadResidencia;
  private Date fechaNacimiento;
  private Integer edad;

  private PersonaDTO(final Builder builder) {
    setId(builder.id);
    setTipoDocumento(builder.tipoDocumento);
    setNumeroIdentificacion(builder.numeroIdentificacion);
    setPrimerNombre(builder.primerNombre);
    setSegundoNombre(builder.segundoNombre);
    setPrimerApellido(builder.primerApellido);
    setSegundoApellido(builder.segundoApellido);
    setNumeroTelefonico(builder.numeroTelefonico);
    setCorreoElectronico(builder.correoElectronico);
    setDireccionResidencia(builder.direccionResidencia);
    setCiudadResidencia(builder.ciudadResidencia);
    setFechaNacimiento(builder.fechaNacimiento);
    setEdad(builder.edad);
  }

  public UUID getId() {
    return id;
  }

  public TipoDocumentoDTO getTipoDocumento() {
    return tipoDocumento;
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public String getPrimerNombre() {
    return primerNombre;
  }

  public String getSegundoNombre() {
    return segundoNombre;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public String getNumeroTelefonico() {
    return numeroTelefonico;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public String getDireccionResidencia() {
    return direccionResidencia;
  }

  public CiudadDTO getCiudadResidencia() {
    return ciudadResidencia;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public Integer getEdad() {
    return edad;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  private void setNumeroIdentificacion(final String numeroIdentificacion) {
    this.numeroIdentificacion = UtilTexto.aplicarTrim(numeroIdentificacion);
  }

  private void setPrimerNombre(final String primerNombre) {
    this.primerNombre = UtilTexto.aplicarTrim(primerNombre);
  }

  private void setSegundoNombre(final String segundoNombre) {
    this.segundoNombre = UtilTexto.aplicarTrim(segundoNombre);
  }

  private void setPrimerApellido(final String primerApellido) {
    this.primerApellido = UtilTexto.aplicarTrim(primerApellido);
  }

  private void setSegundoApellido(final String segundoApellido) {
    this.segundoApellido = UtilTexto.aplicarTrim(segundoApellido);
  }

  private void setNumeroTelefonico(final String numeroTelefonico) {
    this.numeroTelefonico = UtilTexto.aplicarTrim(numeroTelefonico);
  }

  private void setCorreoElectronico(final String correoElectronico) {
    this.correoElectronico = UtilTexto.aplicarTrim(correoElectronico);
  }

  private void setDireccionResidencia(final String direccionResidencia) {
    this.direccionResidencia = UtilTexto.aplicarTrim(direccionResidencia);
  }

  private void setCiudadResidencia(final CiudadDTO ciudadResidencia) {
    this.ciudadResidencia = ciudadResidencia;
  }

  private void setFechaNacimiento(final Date fechaNacimiento) {
    this.fechaNacimiento = UtilDate.obtenerValorDefecto(fechaNacimiento);
  }

  private void setEdad(final Integer edad) {
    this.edad = UtilNumero.obtenerValorDefecto(edad);
  }

  public static class Builder {
    private UUID id;
    private TipoDocumentoDTO tipoDocumento;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String numeroTelefonico;
    private String correoElectronico;
    private String direccionResidencia;
    private CiudadDTO ciudadResidencia;
    private Date fechaNacimiento;
    private Integer edad;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder tipoDocumento(final TipoDocumentoDTO tipoDocumento) {
      this.tipoDocumento = tipoDocumento;
      return this;
    }

    public Builder numeroIdentificacion(final String numeroIdentificacion) {
      this.numeroIdentificacion = numeroIdentificacion;
      return this;
    }

    public Builder primerNombre(final String primerNombre) {
      this.primerNombre = primerNombre;
      return this;
    }

    public Builder segundoNombre(final String segundoNombre) {
      this.segundoNombre = segundoNombre;
      return this;
    }

    public Builder primerApellido(final String primerApellido) {
      this.primerApellido = primerApellido;
      return this;
    }

    public Builder segundoApellido(final String segundoApellido) {
      this.segundoApellido = segundoApellido;
      return this;
    }

    public Builder numeroTelefonico(final String numeroTelefonico) {
      this.numeroTelefonico = numeroTelefonico;
      return this;
    }

    public Builder correoElectronico(final String correoElectronico) {
      this.correoElectronico = correoElectronico;
      return this;
    }

    public Builder direccionResidencia(final String direccionResidencia) {
      this.direccionResidencia = direccionResidencia;
      return this;
    }

    public Builder ciudadResidencia(final CiudadDTO ciudadResidencia) {
      this.ciudadResidencia = ciudadResidencia;
      return this;
    }

    public Builder fechaNacimiento(final Date fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
      return this;
    }

    public Builder edad(final Integer edad) {
      this.edad = edad;
      return this;
    }

    public PersonaDTO build() {
      return new PersonaDTO(this);
    }
  }
}
