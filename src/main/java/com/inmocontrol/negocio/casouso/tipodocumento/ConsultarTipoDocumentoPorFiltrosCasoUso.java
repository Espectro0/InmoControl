package com.inmocontrol.negocio.casouso.tipodocumento;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import java.util.List;

public interface ConsultarTipoDocumentoPorFiltrosCasoUso
		extends CasoUsoConRetorno<TipoDocumentoDominio, List<TipoDocumentoEntidad>> {
}