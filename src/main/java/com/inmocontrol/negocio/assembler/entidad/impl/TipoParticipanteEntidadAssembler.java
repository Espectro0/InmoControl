package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoParticipanteEntidadAssembler
        implements EntidadAssembler<TipoParticipanteEntidad, TipoParticipanteDominio> {
    private static EntidadAssembler<TipoParticipanteEntidad, TipoParticipanteDominio> INSTANCE;

    private TipoParticipanteEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<
                    TipoParticipanteEntidad, TipoParticipanteDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new TipoParticipanteEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public TipoParticipanteDominio ensamblarDominio(final TipoParticipanteEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        entidad, new TipoParticipanteEntidad.Builder().build());
        return new TipoParticipanteDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public TipoParticipanteEntidad ensamblarEntidad(final TipoParticipanteDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new TipoParticipanteDominio.Builder().build());
        return new TipoParticipanteEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
