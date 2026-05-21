package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoParticipanteDTOAssembler
    implements DTOAssembler<TipoParticipanteDominio, TipoParticipanteDTO> {
  private static DTOAssembler<TipoParticipanteDominio, TipoParticipanteDTO> INSTANCE;

  private TipoParticipanteDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<TipoParticipanteDominio, TipoParticipanteDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new TipoParticipanteDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public TipoParticipanteDominio ensamblarDominio(final TipoParticipanteDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new TipoParticipanteDTO.Builder().build());
    return new TipoParticipanteDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .build();
  }

  @Override
  public TipoParticipanteDTO ensamblarDTO(final TipoParticipanteDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new TipoParticipanteDominio.Builder().build());
    return new TipoParticipanteDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .build();
  }
}
