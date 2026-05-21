package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ClausulaPorContratoEntidadAssembler
    implements EntidadAssembler<ClausulaPorContratoEntidad, ClausulaPorContratoDominio> {
  private static EntidadAssembler<ClausulaPorContratoEntidad, ClausulaPorContratoDominio> INSTANCE;

  private ClausulaPorContratoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<
          ClausulaPorContratoEntidad, ClausulaPorContratoDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ClausulaPorContratoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ClausulaPorContratoDominio ensamblarDominio(final ClausulaPorContratoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new ClausulaPorContratoEntidad.Builder().build());
    return new ClausulaPorContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .numeroClausula(entidadAEnsamblar.getNumeroClausula())
        .contrato(
            ContratoEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getContrato()))
        .clausula(
            ClausulaContratoEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getClausula()))
        .build();
  }

  @Override
  public ClausulaPorContratoEntidad ensamblarEntidad(final ClausulaPorContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new ClausulaPorContratoDominio.Builder().build());
    return new ClausulaPorContratoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .numeroClausula(dominioAEnsamblar.getNumeroClausula())
        .contrato(
            ContratoEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getContrato()))
        .clausula(
            ClausulaContratoEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getClausula()))
        .build();
  }
}
