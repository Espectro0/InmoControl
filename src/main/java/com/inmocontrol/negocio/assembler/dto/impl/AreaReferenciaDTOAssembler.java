package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class AreaReferenciaDTOAssembler
    implements DTOAssembler<AreaReferenciaDominio, AreaReferenciaDTO> {
  private static DTOAssembler<AreaReferenciaDominio, AreaReferenciaDTO> INSTANCE;

  private AreaReferenciaDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<AreaReferenciaDominio, AreaReferenciaDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new AreaReferenciaDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public AreaReferenciaDominio ensamblarDominio(final AreaReferenciaDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new AreaReferenciaDTO.Builder().build());
    return new AreaReferenciaDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .build();
  }

  @Override
  public AreaReferenciaDTO ensamblarDTO(final AreaReferenciaDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new AreaReferenciaDominio.Builder().build());
    return new AreaReferenciaDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .build();
  }
}
