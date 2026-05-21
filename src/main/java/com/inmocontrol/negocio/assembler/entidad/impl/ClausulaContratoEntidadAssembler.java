package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ClausulaContratoEntidadAssembler
    implements EntidadAssembler<ClausulaContratoEntidad, ClausulaContratoDominio> {
  private static EntidadAssembler<ClausulaContratoEntidad, ClausulaContratoDominio> INSTANCE;

  private ClausulaContratoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<
          ClausulaContratoEntidad, ClausulaContratoDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ClausulaContratoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ClausulaContratoDominio ensamblarDominio(final ClausulaContratoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new ClausulaContratoEntidad.Builder().build());
    return new ClausulaContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .areaReferencia(
            AreaReferenciaEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getAreaReferencia()))
        .tipoAplicacion(
            TipoAplicacionEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getTipoAplicacion()))
        .titulo(entidadAEnsamblar.getTitulo())
        .contenidoLegal(entidadAEnsamblar.getContenidoLegal())
        .build();
  }

  @Override
  public ClausulaContratoEntidad ensamblarEntidad(final ClausulaContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new ClausulaContratoDominio.Builder().build());
    return new ClausulaContratoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .areaReferencia(
            AreaReferenciaEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getAreaReferencia()))
        .tipoAplicacion(
            TipoAplicacionEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getTipoAplicacion()))
        .titulo(dominioAEnsamblar.getTitulo())
        .contenidoLegal(dominioAEnsamblar.getContenidoLegal())
        .build();
  }
}
