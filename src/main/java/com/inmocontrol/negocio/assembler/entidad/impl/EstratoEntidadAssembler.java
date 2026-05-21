package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class EstratoEntidadAssembler
    implements EntidadAssembler<EstratoEntidad, EstratoDominio> {
  private static EntidadAssembler<EstratoEntidad, EstratoDominio> INSTANCE;

  private EstratoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<EstratoEntidad, EstratoDominio> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new EstratoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public EstratoDominio ensamblarDominio(final EstratoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new EstratoEntidad.Builder().build());
    return new EstratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .descripcion(entidadAEnsamblar.getDescripcion())
        .build();
  }

  @Override
  public EstratoEntidad ensamblarEntidad(final EstratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new EstratoDominio.Builder().build());
    return new EstratoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .descripcion(dominioAEnsamblar.getDescripcion())
        .build();
  }
}
