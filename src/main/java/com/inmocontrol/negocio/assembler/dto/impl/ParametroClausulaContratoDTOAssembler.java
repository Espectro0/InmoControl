package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ParametroClausulaContratoDTOAssembler
    implements DTOAssembler<ParametroClausulaContratoDominio, ParametroClausulaContratoDTO> {
  private static DTOAssembler<ParametroClausulaContratoDominio, ParametroClausulaContratoDTO>
      INSTANCE;

  private ParametroClausulaContratoDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<
          ParametroClausulaContratoDominio, ParametroClausulaContratoDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ParametroClausulaContratoDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ParametroClausulaContratoDominio ensamblarDominio(final ParametroClausulaContratoDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new ParametroClausulaContratoDTO.Builder().build());
    return new ParametroClausulaContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .parametro(
            ParametroDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getParametro()))
        .clausulaPorContrato(
            ClausulaPorContratoDTOAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getClausulaPorContrato()))
        .valor(entidadAEnsamblar.getValor())
        .build();
  }

  @Override
  public ParametroClausulaContratoDTO ensamblarDTO(final ParametroClausulaContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(
            dominio, new ParametroClausulaContratoDominio.Builder().build());
    return new ParametroClausulaContratoDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .parametro(
            ParametroDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getParametro()))
        .clausulaPorContrato(
            ClausulaPorContratoDTOAssembler.getInstance()
                .ensamblarDTO(dominioAEnsamblar.getClausulaPorContrato()))
        .valor(dominioAEnsamblar.getValor())
        .build();
  }
}
