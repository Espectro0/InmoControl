package com.inmocontrol.negocio.casouso.parametro;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.entidad.ParametroEntidad;
import java.util.List;

public interface ConsultarParametroPorFiltrosCasoUso extends CasoUsoConRetorno<ParametroDominio, List<ParametroEntidad>> {
}