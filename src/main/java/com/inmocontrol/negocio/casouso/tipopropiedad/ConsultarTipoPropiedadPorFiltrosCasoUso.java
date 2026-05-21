package com.inmocontrol.negocio.casouso.tipopropiedad;

import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import java.util.List;

public interface ConsultarTipoPropiedadPorFiltrosCasoUso
    extends CasoUsoConRetorno<TipoPropiedadDominio, List<TipoPropiedadEntidad>> {}
