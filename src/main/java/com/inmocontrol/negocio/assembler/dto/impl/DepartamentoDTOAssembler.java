package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class DepartamentoDTOAssembler
        implements DTOAssembler<DepartamentoDominio, DepartamentoDTO> {
    private static DTOAssembler<DepartamentoDominio, DepartamentoDTO> INSTANCE;

    private DepartamentoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<DepartamentoDominio, DepartamentoDTO>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new DepartamentoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public DepartamentoDominio ensamblarDominio(final DepartamentoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new DepartamentoDTO.Builder().build());
        return new DepartamentoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .pais(PaisDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getPais()))
                .build();
    }

    @Override
    public DepartamentoDTO ensamblarDTO(final DepartamentoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new DepartamentoDominio.Builder().build());
        return new DepartamentoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .pais(PaisDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getPais()))
                .build();
    }
}
