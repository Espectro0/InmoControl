package com.inmocontrol.negocio.fachada.contrato;

import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarContratoPorFiltrosFachada
    extends FachadaConRetorno<ContratoDTO, List<ContratoEntidad>> {}
