package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PersonaDTOAssembler implements DTOAssembler<PersonaDominio, PersonaDTO> {
  private static DTOAssembler<PersonaDominio, PersonaDTO> INSTANCE;

  private PersonaDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<PersonaDominio, PersonaDTO> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new PersonaDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public PersonaDominio ensamblarDominio(final PersonaDTO dto) {
    var entidadAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new PersonaDTO.Builder().build());
    return new PersonaDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .tipoDocumento(
            TipoDocumentoDTOAssembler.getInstance()
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
            CiudadDTOAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getCiudadResidencia()))
        .fechaNacimiento(entidadAEnsamblar.getFechaNacimiento())
        .edad(entidadAEnsamblar.getEdad())
        .build();
  }

  @Override
  public PersonaDTO ensamblarDTO(final PersonaDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new PersonaDominio.Builder().build());
    return new PersonaDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .tipoDocumento(
            TipoDocumentoDTOAssembler.getInstance()
                .ensamblarDTO(dominioAEnsamblar.getTipoDocumento()))
        .numeroIdentificacion(dominioAEnsamblar.getNumeroIdentificacion())
        .primerNombre(dominioAEnsamblar.getPrimerNombre())
        .segundoNombre(dominioAEnsamblar.getSegundoNombre())
        .primerApellido(dominioAEnsamblar.getPrimerApellido())
        .segundoApellido(dominioAEnsamblar.getSegundoApellido())
        .numeroTelefonico(dominioAEnsamblar.getNumeroTelefonico())
        .correoElectronico(dominioAEnsamblar.getCorreoElectronico())
        .direccionResidencia(dominioAEnsamblar.getDireccionResidencia())
        .ciudadResidencia(
            CiudadDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getCiudadResidencia()))
        .fechaNacimiento(dominioAEnsamblar.getFechaNacimiento())
        .edad(dominioAEnsamblar.getEdad())
        .build();
  }
}
