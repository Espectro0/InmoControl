package com.inmocontrol.negocio.assembler.entidad.impl;

import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.assembler.entidad.EntidadAssembler;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public final class TipoDocumentoEntidadAssembler
    implements EntidadAssembler<TipoDocumentoEntidad, TipoDocumentoDominio> {
  private static EntidadAssembler<TipoDocumentoEntidad, TipoDocumentoDominio> INSTANCE;

  private TipoDocumentoEntidadAssembler() {
    super();
  }

  public static final synchronized EntidadAssembler<TipoDocumentoEntidad, TipoDocumentoDominio>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new TipoDocumentoEntidadAssembler();
    }
    return INSTANCE;
  }

  @Override
  public TipoDocumentoDominio ensamblarDominio(final TipoDocumentoEntidad entidad) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(entidad, new TipoDocumentoEntidad.Builder().build());
    return new TipoDocumentoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .nombre(entidadAEnsamblar.getNombre())
        .build();
  }

  @Override
  public TipoDocumentoEntidad ensamblarEntidad(final TipoDocumentoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new TipoDocumentoDominio.Builder().build());
    return new TipoDocumentoEntidad.Builder()
        .id(dominioAEnsamblar.getId())
        .nombre(dominioAEnsamblar.getNombre())
        .build();
  }
}
