package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class AreaReferenciaEntidadAssembler
        implements EntidadAssembler<AreaReferenciaEntidad, AreaReferenciaDominio> {
    private static EntidadAssembler<AreaReferenciaEntidad, AreaReferenciaDominio> INSTANCE;

    private AreaReferenciaEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<AreaReferenciaEntidad, AreaReferenciaDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new AreaReferenciaEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public AreaReferenciaDominio ensamblarDominio(final AreaReferenciaEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        entidad, new AreaReferenciaEntidad.Builder().build());
        return new AreaReferenciaDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public AreaReferenciaEntidad ensamblarEntidad(final AreaReferenciaDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new AreaReferenciaDominio.Builder().build());
        return new AreaReferenciaEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
