package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ClausulaPorContratoDTOAssembler
        implements DTOAssembler<ClausulaPorContratoDominio, ClausulaPorContratoDTO> {
    private static DTOAssembler<ClausulaPorContratoDominio, ClausulaPorContratoDTO> INSTANCE;

    private ClausulaPorContratoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<
                    ClausulaPorContratoDominio, ClausulaPorContratoDTO>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ClausulaPorContratoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ClausulaPorContratoDominio ensamblarDominio(final ClausulaPorContratoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new ClausulaPorContratoDTO.Builder().build());
        return new ClausulaPorContratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .numeroClausula(entidadAEnsamblar.getNumeroClausula())
                .contrato(
                        ContratoDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getContrato()))
                .clausula(
                        ClausulaContratoDTOAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getClausula()))
                .build();
    }

    @Override
    public ClausulaPorContratoDTO ensamblarDTO(final ClausulaPorContratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new ClausulaPorContratoDominio.Builder().build());
        return new ClausulaPorContratoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .numeroClausula(dominioAEnsamblar.getNumeroClausula())
                .contrato(
                        ContratoDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getContrato()))
                .clausula(
                        ClausulaContratoDTOAssembler.getInstance()
                                .ensamblarDTO(dominioAEnsamblar.getClausula()))
                .build();
    }
}
