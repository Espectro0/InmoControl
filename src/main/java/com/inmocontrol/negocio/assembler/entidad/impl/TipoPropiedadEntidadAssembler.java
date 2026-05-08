package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoPropiedadEntidadAssembler
        implements EntidadAssembler<TipoPropiedadEntidad, TipoPropiedadDominio> {
    private static EntidadAssembler<TipoPropiedadEntidad, TipoPropiedadDominio> INSTANCE;

    private TipoPropiedadEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<TipoPropiedadEntidad, TipoPropiedadDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new TipoPropiedadEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public TipoPropiedadDominio ensamblarDominio(final TipoPropiedadEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(entidad, new TipoPropiedadEntidad.Builder().build());
        return new TipoPropiedadDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public TipoPropiedadEntidad ensamblarEntidad(final TipoPropiedadDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new TipoPropiedadDominio.Builder().build());
        return new TipoPropiedadEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
