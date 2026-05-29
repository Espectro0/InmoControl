package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

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
    if (entidadAEnsamblar.getParametro().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El parametro es obligatorio");
    }
    if (entidadAEnsamblar.getClausulaPorContrato().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("La clausula por contrato es obligatoria");
    }
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
