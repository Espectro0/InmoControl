package com.inmocontrol.negocio.fachada.contrato;

import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;

public interface ConsultarContratoPorIdFachada
    extends FachadaConRetorno<ContratoDTO, ContratoEntidad> {}
