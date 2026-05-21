package com.inmocontrol.negocio.casouso.tipoaplicacion;

import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;

public interface ConsultarTipoAplicacionPorIdCasoUso
    extends CasoUsoConRetorno<TipoAplicacionDominio, TipoAplicacionEntidad> {}
