package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class ParametroEntidadAssembler
        implements EntidadAssembler<ParametroEntidad, ParametroDominio> {
    private static EntidadAssembler<ParametroEntidad, ParametroDominio> INSTANCE;

    private ParametroEntidadAssembler() {
        super();
    }

    public static final synchronized EntidadAssembler<ParametroEntidad, ParametroDominio>
            getInstance() {
        if (UtilObjeto.esNulo(INSTANCE)) {
            INSTANCE = new ParametroEntidadAssembler();
        }
        return INSTANCE;
    }

    @Override
    public ParametroDominio ensamblarDominio(final ParametroEntidad entidad) {
        var entidadAEnsamblar =
                UtilObjeto.obtenerValorDefecto(entidad, new ParametroEntidad.Builder().build());
        return new ParametroDominio.Builder()
                .id(entidadAEnsamblar.getId())
                .placeholder(entidadAEnsamblar.getPlaceholder())
                .descripcion(entidadAEnsamblar.getDescripcion())
                .build();
    }

    @Override
    public ParametroEntidad ensamblarEntidad(final ParametroDominio dominio) {
        var dominioAEnsamblar =
                UtilObjeto.obtenerValorDefecto(dominio, new ParametroDominio.Builder().build());
        return new ParametroEntidad.Builder()
                .id(dominioAEnsamblar.getId())
                .placeholder(dominioAEnsamblar.getPlaceholder())
                .descripcion(dominioAEnsamblar.getDescripcion())
                .build();
    }
}
