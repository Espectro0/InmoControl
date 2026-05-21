package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoAplicacionDTOAssembler
    implements DTOAssembler<TipoAplicacionDominio, TipoAplicacionDTO> {
  private static DTOAssembler<TipoAplicacionDominio, TipoAplicacionDTO> INSTANCE;

  private TipoAplicacionDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<TipoAplicacionDominio, TipoAplicacionDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new TipoAplicacionDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public TipoAplicacionDominio ensamblarDominio(final TipoAplicacionDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new TipoAplicacionDTO.Builder().build());
    return new TipoAplicacionDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .build();
  }

  @Override
  public TipoAplicacionDTO ensamblarDTO(final TipoAplicacionDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new TipoAplicacionDominio.Builder().build());
    return new TipoAplicacionDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .build();
  }
}
