package com.inmocontrol.negocio.casouso.pais;

import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.PaisDominio;
import java.util.List;

public interface ConsultarPaisPorFiltrosCasoUso
    extends CasoUsoConRetorno<PaisDominio, List<PaisEntidad>> {}
