package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class EstratoDTOAssembler implements DTOAssembler<EstratoDominio, EstratoDTO> {
    private static DTOAssembler<EstratoDominio, EstratoDTO> INSTANCE;

    private EstratoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<EstratoDominio, EstratoDTO> getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new EstratoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public EstratoDominio ensamblarDominio(final EstratoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new EstratoDTO.Builder().build());
        return new EstratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .descripcion(entidadAEnsamblar.getDescripcion())
                .build();
    }

    @Override
    public EstratoDTO ensamblarDTO(final EstratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new EstratoDominio.Builder().build());
        return new EstratoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .descripcion(dominioAEnsamblar.getDescripcion())
                .build();
    }
}
