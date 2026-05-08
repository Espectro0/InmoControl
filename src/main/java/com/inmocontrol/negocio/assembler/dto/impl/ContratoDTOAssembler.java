package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ContratoDTOAssembler implements DTOAssembler<ContratoDominio, ContratoDTO> {
    private static DTOAssembler<ContratoDominio, ContratoDTO> INSTANCE;

    private ContratoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<ContratoDominio, ContratoDTO> getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ContratoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ContratoDominio ensamblarDominio(final ContratoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new ContratoDTO.Builder().build());
        return new ContratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .codigoContrato(entidadAEnsamblar.getCodigoContrato())
                .fechaInicio(entidadAEnsamblar.getFechaInicio())
                .fechaFin(entidadAEnsamblar.getFechaFin())
                .esActivo(entidadAEnsamblar.getEsActivo())
                .propiedad(
                        PropiedadDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getPropiedad()))
                .build();
    }

    @Override
    public ContratoDTO ensamblarDTO(final ContratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new ContratoDominio.Builder().build());
        return new ContratoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .codigoContrato(dominioAEnsamblar.getCodigoContrato())
                .fechaInicio(dominioAEnsamblar.getFechaInicio())
                .fechaFin(dominioAEnsamblar.getFechaFin())
                .esActivo(dominioAEnsamblar.getEsActivo())
                .propiedad(
                        PropiedadDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getPropiedad()))
                .build();
    }
}
