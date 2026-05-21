package com.inmocontrol.negocio.casouso.estrato;

import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import java.util.List;

public interface ConsultarEstratoPorFiltrosCasoUso
    extends CasoUsoConRetorno<EstratoDominio, List<EstratoEntidad>> {}
