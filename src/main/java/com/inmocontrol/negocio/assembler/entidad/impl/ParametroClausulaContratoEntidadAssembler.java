package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ParametroClausulaContratoEntidadAssembler
    implements EntidadAssembler<
        ParametroClausulaContratoEntidad, ParametroClausulaContratoDominio> {
  private static EntidadAssembler<
          ParametroClausulaContratoEntidad, ParametroClausulaContratoDominio>
      INSTANCE;

  private ParametroClausulaContratoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<
          ParametroClausulaContratoEntidad, ParametroClausulaContratoDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ParametroClausulaContratoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ParametroClausulaContratoDominio ensamblarDominio(
      final ParametroClausulaContratoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(
            entidad, new ParametroClausulaContratoEntidad.Builder().build());
    return new ParametroClausulaContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .parametro(
            ParametroEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getParametro()))
        .clausulaPorContrato(
            ClausulaPorContratoEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getClausulaPorContrato()))
        .valor(entidadAEnsamblar.getValor())
        .build();
  }

  @Override
  public ParametroClausulaContratoEntidad ensamblarEntidad(
      final ParametroClausulaContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(
            dominio, new ParametroClausulaContratoDominio.Builder().build());
    return new ParametroClausulaContratoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .parametro(
            ParametroEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getParametro()))
        .clausulaPorContrato(
            ClausulaPorContratoEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getClausulaPorContrato()))
        .valor(dominioAEnsamblar.getValor())
        .build();
  }
}
