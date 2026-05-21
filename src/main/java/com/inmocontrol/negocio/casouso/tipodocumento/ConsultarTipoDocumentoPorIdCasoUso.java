package com.inmocontrol.negocio.casouso.tipodocumento;

import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;

public interface ConsultarTipoDocumentoPorIdCasoUso
    extends CasoUsoConRetorno<TipoDocumentoDominio, TipoDocumentoEntidad> {}
