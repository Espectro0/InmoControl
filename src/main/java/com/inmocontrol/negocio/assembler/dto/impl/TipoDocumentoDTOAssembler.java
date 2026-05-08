package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoDocumentoDTOAssembler
        implements DTOAssembler<TipoDocumentoDominio, TipoDocumentoDTO> {
    private static DTOAssembler<TipoDocumentoDominio, TipoDocumentoDTO> INSTANCE;

    private TipoDocumentoDTOAssembler() {
        super();
    }

    public static final synchronized DTOAssembler<TipoDocumentoDominio, TipoDocumentoDTO>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new TipoDocumentoDTOAssembler();
        }
        return INSTANCE;
    }

    @Override
    public TipoDocumentoDominio ensamblarDominio(final TipoDocumentoDTO dto) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dto, new TipoDocumentoDTO.Builder().build());
        return new TipoDocumentoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public TipoDocumentoDTO ensamblarDTO(final TipoDocumentoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new TipoDocumentoDominio.Builder().build());
        return new TipoDocumentoDTO.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
