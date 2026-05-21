package com.inmocontrol.negocio.casouso.tipopropiedad;

import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;

public interface ConsultarTipoPropiedadPorIdCasoUso
    extends CasoUsoConRetorno<TipoPropiedadDominio, TipoPropiedadEntidad> {}
