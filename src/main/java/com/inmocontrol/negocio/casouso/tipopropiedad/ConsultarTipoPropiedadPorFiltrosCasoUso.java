package com.inmocontrol.negocio.casouso.tipopropiedad;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import java.util.List;

public interface ConsultarTipoPropiedadPorFiltrosCasoUso extends CasoUsoConRetorno<TipoPropiedadDominio, List<TipoPropiedadEntidad>> {
}