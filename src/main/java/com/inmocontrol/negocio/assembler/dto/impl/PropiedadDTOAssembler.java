package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PropiedadDTOAssembler implements DTOAssembler<PropiedadDominio, PropiedadDTO> {
  private static DTOAssembler<PropiedadDominio, PropiedadDTO> INSTANCE;

  private PropiedadDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<PropiedadDominio, PropiedadDTO> getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new PropiedadDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public PropiedadDominio ensamblarDominio(final PropiedadDTO dto) {
    var entidadAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new PropiedadDTO.Builder().build());
    return new PropiedadDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .tipoPropiedad(
            TipoPropiedadDTOAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getTipoPropiedad()))
        .estrato(EstratoDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getEstrato()))
        .nombreInmueble(entidadAEnsamblar.getNombreInmueble())
        .descripcionInmueble(entidadAEnsamblar.getDescripcionInmueble())
        .areaMetros(entidadAEnsamblar.getAreaMetros())
        .direccion(entidadAEnsamblar.getDireccion())
        .ciudad(CiudadDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getCiudad()))
        .build();
  }

  @Override
  public PropiedadDTO ensamblarDTO(final PropiedadDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new PropiedadDominio.Builder().build());
    return new PropiedadDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .tipoPropiedad(
            TipoPropiedadDTOAssembler.getInstance()
                .ensamblarDTO(dominioAEnsamblar.getTipoPropiedad()))
        .estrato(EstratoDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getEstrato()))
        .nombreInmueble(dominioAEnsamblar.getNombreInmueble())
        .descripcionInmueble(dominioAEnsamblar.getDescripcionInmueble())
        .areaMetros(dominioAEnsamblar.getAreaMetros())
        .direccion(dominioAEnsamblar.getDireccion())
        .ciudad(CiudadDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getCiudad()))
        .build();
  }
}
