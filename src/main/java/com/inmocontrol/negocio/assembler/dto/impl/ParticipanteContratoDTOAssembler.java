package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

public final class ParticipanteContratoDTOAssembler
    implements DTOAssembler<ParticipanteContratoDominio, ParticipanteContratoDTO> {
  private static DTOAssembler<ParticipanteContratoDominio, ParticipanteContratoDTO> INSTANCE;

  private ParticipanteContratoDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<
          ParticipanteContratoDominio, ParticipanteContratoDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ParticipanteContratoDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ParticipanteContratoDominio ensamblarDominio(final ParticipanteContratoDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new ParticipanteContratoDTO.Builder().build());
    if (entidadAEnsamblar.getPersona().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("La persona es obligatoria");
    }
    if (entidadAEnsamblar.getTipoParticipante().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El tipo de participante es obligatorio");
    }
    if (entidadAEnsamblar.getContrato().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El contrato es obligatorio");
    }
    return new ParticipanteContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .persona(PersonaDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getPersona()))
        .tipoParticipante(
            TipoParticipanteDTOAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getTipoParticipante()))
        .contrato(
            ContratoDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getContrato()))
        .build();
  }

  @Override
  public ParticipanteContratoDTO ensamblarDTO(final ParticipanteContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new ParticipanteContratoDominio.Builder().build());
    return new ParticipanteContratoDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .persona(PersonaDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getPersona()))
        .tipoParticipante(
            TipoParticipanteDTOAssembler.getInstance()
                .ensamblarDTO(dominioAEnsamblar.getTipoParticipante()))
        .contrato(ContratoDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getContrato()))
        .build();
  }
}
