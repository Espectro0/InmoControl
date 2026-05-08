package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ClausulaContratoDTOAssembler
        implements DTOAssembler<ClausulaContratoDominio, ClausulaContratoDTO> {
    private static DTOAssembler<ClausulaContratoDominio, ClausulaContratoDTO> INSTANCE;

    private ClausulaContratoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<ClausulaContratoDominio, ClausulaContratoDTO>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ClausulaContratoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ClausulaContratoDominio ensamblarDominio(final ClausulaContratoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new ClausulaContratoDTO.Builder().build());
        return new ClausulaContratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .areaReferencia(
                        AreaReferenciaDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getAreaReferencia()))
                .tipoAplicacion(
                        TipoAplicacionDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getTipoAplicacion()))
                .titulo(entidadAEnsamblar.getTitulo())
                .contenidoLegal(entidadAEnsamblar.getContenidoLegal())
                .build();
    }

    @Override
    public ClausulaContratoDTO ensamblarDTO(final ClausulaContratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new ClausulaContratoDominio.Builder().build());
        return new ClausulaContratoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .areaReferencia(
                        AreaReferenciaDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getAreaReferencia()))
                .tipoAplicacion(
                        TipoAplicacionDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getTipoAplicacion()))
                .titulo(dominioAEnsamblar.getTitulo())
                .contenidoLegal(dominioAEnsamblar.getContenidoLegal())
                .build();
    }
}
