package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoAplicacionEntidadAssembler
        implements EntidadAssembler<TipoAplicacionEntidad, TipoAplicacionDominio> {
    private static EntidadAssembler<TipoAplicacionEntidad, TipoAplicacionDominio> INSTANCE;

    private TipoAplicacionEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<TipoAplicacionEntidad, TipoAplicacionDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new TipoAplicacionEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public TipoAplicacionDominio ensamblarDominio(final TipoAplicacionEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        entidad, new TipoAplicacionEntidad.Builder().build());
        return new TipoAplicacionDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public TipoAplicacionEntidad ensamblarEntidad(final TipoAplicacionDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(
                        dominio, new TipoAplicacionDominio.Builder().build());
        return new TipoAplicacionEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
