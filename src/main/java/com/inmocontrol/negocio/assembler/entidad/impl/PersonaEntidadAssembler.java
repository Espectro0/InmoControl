package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PersonaEntidadAssembler
    implements EntidadAssembler<PersonaEntidad, PersonaDominio> {
  private static EntidadAssembler<PersonaEntidad, PersonaDominio> INSTANCE;

  private PersonaEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<PersonaEntidad, PersonaDominio> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new PersonaEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public PersonaDominio ensamblarDominio(final PersonaEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new PersonaEntidad.Builder().build());
    return new PersonaDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .tipoDocumento(
            TipoDocumentoEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getTipoDocumento()))
        .numeroIdentificacion(entidadAEnsamblar.getNumeroIdentificacion())
        .primerNombre(entidadAEnsamblar.getPrimerNombre())
        .segundoNombre(entidadAEnsamblar.getSegundoNombre())
        .primerApellido(entidadAEnsamblar.getPrimerApellido())
        .segundoApellido(entidadAEnsamblar.getSegundoApellido())
        .numeroTelefonico(entidadAEnsamblar.getNumeroTelefonico())
        .correoElectronico(entidadAEnsamblar.getCorreoElectronico())
        .direccionResidencia(entidadAEnsamblar.getDireccionResidencia())
        .ciudadResidencia(
            CiudadEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getCiudadResidencia()))
        .fechaNacimiento(entidadAEnsamblar.getFechaNacimiento())
        .edad(entidadAEnsamblar.getEdad())
        .build();
  }

  @Override
  public PersonaEntidad ensamblarEntidad(final PersonaDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new PersonaDominio.Builder().build());
    return new PersonaEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .tipoDocumento(
            TipoDocumentoEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getTipoDocumento()))
        .numeroIdentificacion(dominioAEnsamblar.getNumeroIdentificacion())
        .primerNombre(dominioAEnsamblar.getPrimerNombre())
        .segundoNombre(dominioAEnsamblar.getSegundoNombre())
        .primerApellido(dominioAEnsamblar.getPrimerApellido())
        .segundoApellido(dominioAEnsamblar.getSegundoApellido())
        .numeroTelefonico(dominioAEnsamblar.getNumeroTelefonico())
        .correoElectronico(dominioAEnsamblar.getCorreoElectronico())
        .direccionResidencia(dominioAEnsamblar.getDireccionResidencia())
        .ciudadResidencia(
            CiudadEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getCiudadResidencia()))
        .fechaNacimiento(dominioAEnsamblar.getFechaNacimiento())
        .edad(dominioAEnsamblar.getEdad())
        .build();
  }
}
