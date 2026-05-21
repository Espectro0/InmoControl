package com.inmocontrol.negocio.fachada.estrato;

import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarEstratoPorFiltrosFachada
    extends FachadaConRetorno<EstratoDTO, List<EstratoEntidad>> {}
