package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class PaisEntidadAssembler implements EntidadAssembler<PaisEntidad, PaisDominio> {
    private static EntidadAssembler<PaisEntidad, PaisDominio> INSTANCE;

    private PaisEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<PaisEntidad, PaisDominio> getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new PaisEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public PaisDominio ensamblarDominio(final PaisEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(entidad, new PaisEntidad.Builder().build());
        return new PaisDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .nombre(entidadAEnsamblar.getNombre())
                .build();
    }

    @Override
    public PaisEntidad ensamblarEntidad(final PaisDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new PaisDominio.Builder().build());
        return new PaisEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .nombre(dominioAEnsamblar.getNombre())
                .build();
    }
}
