package com.inmocontrol.negocio.fachada.departamento;

import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarDepartamentoPorFiltrosFachada
    extends FachadaConRetorno<DepartamentoDTO, List<DepartamentoEntidad>> {}
