package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO> {
    private static DTOAssembler<PaisDominio, PaisDTO> INSTANCE;

    private PaisDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<PaisDominio, PaisDTO> getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new PaisDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public PaisDominio ensamblarDominio(final PaisDTO dto) {
        var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new PaisDTO.Builder().build());
        return new PaisDominio.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }

    @Override
    public PaisDTO ensamblarDTO(final PaisDominio dominio) {
        var paisAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new PaisDominio.Builder().build());
        return new PaisDTO.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }
}
