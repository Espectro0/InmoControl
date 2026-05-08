package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ParticipanteContratoEntidadAssembler
        implements EntidadAssembler<ParticipanteContratoEntidad, ParticipanteContratoDominio> {
    private static EntidadAssembler<ParticipanteContratoEntidad, ParticipanteContratoDominio>
            INSTANCE;

    private ParticipanteContratoEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<
                    ParticipanteContratoEntidad, ParticipanteContratoDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ParticipanteContratoEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ParticipanteContratoDominio ensamblarDominio(final ParticipanteContratoEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        entidad, new ParticipanteContratoEntidad.Builder().build());
        return new ParticipanteContratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .persona(
                        PersonaEntidadAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getPersona()))
                .tipoParticipante(
                        TipoParticipanteEntidadAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getTipoParticipante()))
                .contrato(
                        ContratoEntidadAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getContrato()))
                .build();
    }

    @Override
    public ParticipanteContratoEntidad ensamblarEntidad(final ParticipanteContratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new ParticipanteContratoDominio.Builder().build());
        return new ParticipanteContratoEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .persona(
                        PersonaEntidadAssembler.getInstance()
                                .ensamblarEntidad(dominioAEnsamblar.getPersona()))
                .tipoParticipante(
                        TipoParticipanteEntidadAssembler.getInstance()
                                .ensamblarEntidad(dominioAEnsamblar.getTipoParticipante()))
                .contrato(
                        ContratoEntidadAssembler.getInstance()
                                .ensamblarEntidad(dominioAEnsamblar.getContrato()))
                .build();
    }
}
