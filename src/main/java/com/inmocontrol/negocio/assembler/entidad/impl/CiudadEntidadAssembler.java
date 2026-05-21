package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class CiudadEntidadAssembler
    implements EntidadAssembler<CiudadEntidad, CiudadDominio> {
  private static EntidadAssembler<CiudadEntidad, CiudadDominio> INSTANCE;

  private CiudadEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<CiudadEntidad, CiudadDominio> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new CiudadEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public CiudadDominio ensamblarDominio(final CiudadEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new CiudadEntidad.Builder().build());
    return new CiudadDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .departamento(
            DepartamentoEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getDepartamento()))
        .build();
  }

  @Override
  public CiudadEntidad ensamblarEntidad(final CiudadDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new CiudadDominio.Builder().build());
    return new CiudadEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .departamento(
            DepartamentoEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getDepartamento()))
        .build();
  }
}
