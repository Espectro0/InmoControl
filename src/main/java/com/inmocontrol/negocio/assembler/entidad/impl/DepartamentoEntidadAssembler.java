package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class DepartamentoEntidadAssembler
    implements EntidadAssembler<DepartamentoEntidad, DepartamentoDominio> {
  private static EntidadAssembler<DepartamentoEntidad, DepartamentoDominio> INSTANCE;

  private DepartamentoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<DepartamentoEntidad, DepartamentoDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new DepartamentoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public DepartamentoDominio ensamblarDominio(final DepartamentoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new DepartamentoEntidad.Builder().build());
    return new DepartamentoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .pais(PaisEntidadAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getPais()))
        .build();
  }

  @Override
  public DepartamentoEntidad ensamblarEntidad(final DepartamentoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new DepartamentoDominio.Builder().build());
    return new DepartamentoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .pais(PaisEntidadAssembler.getInstance().ensamblarEntidad(dominioAEnsamblar.getPais()))
        .build();
  }
}
