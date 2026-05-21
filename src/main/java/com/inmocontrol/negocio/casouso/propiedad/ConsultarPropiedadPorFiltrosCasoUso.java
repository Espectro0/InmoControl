package com.inmocontrol.negocio.casouso.propiedad;

import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import java.util.List;

public interface ConsultarPropiedadPorFiltrosCasoUso
    extends CasoUsoConRetorno<PropiedadDominio, List<PropiedadEntidad>> {}
