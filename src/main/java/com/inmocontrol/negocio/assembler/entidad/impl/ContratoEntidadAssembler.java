package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ContratoEntidadAssembler
        implements EntidadAssembler<ContratoEntidad, ContratoDominio> {
    private static EntidadAssembler<ContratoEntidad, ContratoDominio> INSTANCE;

    private ContratoEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<ContratoEntidad, ContratoDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ContratoEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ContratoDominio ensamblarDominio(final ContratoEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(entidad, new ContratoEntidad.Builder().build());
        return new ContratoDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .codigoContrato(entidadAEnsamblar.getCodigoContrato())
                .fechaInicio(entidadAEnsamblar.getFechaInicio())
                .fechaFin(entidadAEnsamblar.getFechaFin())
                .esActivo(entidadAEnsamblar.getEsActivo())
                .propiedad(
                        PropiedadEntidadAssembler.getInstance()
                                .ensamblarDominio(entidadAEnsamblar.getPropiedad()))
                .build();
    }

    @Override
    public ContratoEntidad ensamblarEntidad(final ContratoDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new ContratoDominio.Builder().build());
        return new ContratoEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .codigoContrato(dominioAEnsamblar.getCodigoContrato())
                .fechaInicio(dominioAEnsamblar.getFechaInicio())
                .fechaFin(dominioAEnsamblar.getFechaFin())
                .esActivo(dominioAEnsamblar.getEsActivo())
                .propiedad(
                        PropiedadEntidadAssembler.getInstance()
                                .ensamblarEntidad(dominioAEnsamblar.getPropiedad()))
                .build();
    }
}
