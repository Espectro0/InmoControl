package com.inmocontrol.negocio.casouso.tipoaplicacion;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import java.util.List;

public interface ConsultarTipoAplicacionPorFiltrosCasoUso
		extends CasoUsoConRetorno<TipoAplicacionDominio, List<TipoAplicacionEntidad>> {
}