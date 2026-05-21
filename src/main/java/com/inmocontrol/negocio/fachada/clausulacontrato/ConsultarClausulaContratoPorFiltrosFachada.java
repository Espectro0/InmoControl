package com.inmocontrol.negocio.fachada.clausulacontrato;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import java.util.List;

public interface ConsultarClausulaContratoPorFiltrosFachada extends FachadaConRetorno<ClausulaContratoDTO, List<ClausulaContratoEntidad>> {
}