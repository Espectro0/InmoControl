package com.inmocontrol.negocio.fachada.parametro;

import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarParametroPorFiltrosFachada extends FachadaConRetorno<ParametroDTO, List<ParametroEntidad>> {
}
