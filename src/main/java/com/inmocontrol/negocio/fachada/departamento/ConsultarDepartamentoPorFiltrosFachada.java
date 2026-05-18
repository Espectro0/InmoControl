package com.inmocontrol.negocio.fachada.departamento;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import java.util.List;

public interface ConsultarDepartamentoPorFiltrosFachada extends FachadaConRetorno<DepartamentoDTO, List<DepartamentoEntidad>> {
}