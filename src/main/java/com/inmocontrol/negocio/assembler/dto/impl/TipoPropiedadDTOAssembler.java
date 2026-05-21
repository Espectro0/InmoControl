package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoPropiedadDTOAssembler
    implements DTOAssembler<TipoPropiedadDominio, TipoPropiedadDTO> {
  private static DTOAssembler<TipoPropiedadDominio, TipoPropiedadDTO> INSTANCE;

  private TipoPropiedadDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<TipoPropiedadDominio, TipoPropiedadDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new TipoPropiedadDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public TipoPropiedadDominio ensamblarDominio(final TipoPropiedadDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new TipoPropiedadDTO.Builder().build());
    return new TipoPropiedadDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .build();
  }

  @Override
  public TipoPropiedadDTO ensamblarDTO(final TipoPropiedadDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new TipoPropiedadDominio.Builder().build());
    return new TipoPropiedadDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .build();
  }
}
