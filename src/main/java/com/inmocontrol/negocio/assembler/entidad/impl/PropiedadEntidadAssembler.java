package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PropiedadEntidadAssembler
    implements EntidadAssembler<PropiedadEntidad, PropiedadDominio> {
  private static EntidadAssembler<PropiedadEntidad, PropiedadDominio> INSTANCE;

  private PropiedadEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<PropiedadEntidad, PropiedadDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new PropiedadEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public PropiedadDominio ensamblarDominio(final PropiedadEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new PropiedadEntidad.Builder().build());
    return new PropiedadDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .tipoPropiedad(
            TipoPropiedadEntidadAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getTipoPropiedad()))
        .estrato(
            EstratoEntidadAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getEstrato()))
        .nombreInmueble(entidadAEnsamblar.getNombreInmueble())
        .descripcionInmueble(entidadAEnsamblar.getDescripcionInmueble())
        .areaMetros(entidadAEnsamblar.getAreaMetros())
        .direccion(entidadAEnsamblar.getDireccion())
        .ciudad(
            CiudadEntidadAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getCiudad()))
        .build();
  }

  @Override
  public PropiedadEntidad ensamblarEntidad(final PropiedadDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new PropiedadDominio.Builder().build());
    return new PropiedadEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .tipoPropiedad(
            TipoPropiedadEntidadAssembler.getInstance()
                .ensamblarEntidad(dominioAEnsamblar.getTipoPropiedad()))
        .estrato(
            EstratoEntidadAssembler.getInstance().ensamblarEntidad(dominioAEnsamblar.getEstrato()))
        .nombreInmueble(dominioAEnsamblar.getNombreInmueble())
        .descripcionInmueble(dominioAEnsamblar.getDescripcionInmueble())
        .areaMetros(dominioAEnsamblar.getAreaMetros())
        .direccion(dominioAEnsamblar.getDireccion())
        .ciudad(
            CiudadEntidadAssembler.getInstance().ensamblarEntidad(dominioAEnsamblar.getCiudad()))
        .build();
  }
}
