package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class CiudadDTOAssembler implements DTOAssembler<CiudadDominio, CiudadDTO> {
    private static DTOAssembler<CiudadDominio, CiudadDTO> INSTANCE;

    private CiudadDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<CiudadDominio, CiudadDTO> getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new CiudadDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public CiudadDominio ensamblarDominio(final CiudadDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new CiudadDTO.Builder().build());
        return new CiudadDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .departamento(
                        DepartamentoDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getDepartamento()))
                .build();
    }

    @Override
    public CiudadDTO ensamblarDTO(final CiudadDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new CiudadDominio.Builder().build());
        return new CiudadDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .departamento(
                        DepartamentoDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getDepartamento()))
                .build();
    }
}
