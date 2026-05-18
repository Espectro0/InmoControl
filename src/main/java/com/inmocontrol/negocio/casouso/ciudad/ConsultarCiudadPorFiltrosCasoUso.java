package com.inmocontrol.negocio.casouso.ciudad;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.entidad.CiudadEntidad;
import java.util.List;

public interface ConsultarCiudadPorFiltrosCasoUso extends CasoUsoConRetorno<CiudadDominio, List<CiudadEntidad>> {
}