package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ParametroDTOAssembler implements DTOAssembler<ParametroDominio, ParametroDTO> {
  private static DTOAssembler<ParametroDominio, ParametroDTO> INSTANCE;

  private ParametroDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<ParametroDominio, ParametroDTO> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ParametroDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ParametroDominio ensamblarDominio(final ParametroDTO dto) {
    var entidadAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new ParametroDTO.Builder().build());
    return new ParametroDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .placeholder(entidadAEnsamblar.getPlaceholder())
        .descripcion(entidadAEnsamblar.getDescripcion())
        .build();
  }

  @Override
  public ParametroDTO ensamblarDTO(final ParametroDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new ParametroDominio.Builder().build());
    return new ParametroDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .placeholder(dominioAEnsamblar.getPlaceholder())
        .descripcion(dominioAEnsamblar.getDescripcion())
        .build();
  }
}
