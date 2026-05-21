package com.inmocontrol.negocio.casouso.areareferencia;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import java.util.List;

public interface ConsultarAreaReferenciaPorFiltrosCasoUso extends CasoUsoConRetorno<AreaReferenciaDominio, List<AreaReferenciaEntidad>> {
}