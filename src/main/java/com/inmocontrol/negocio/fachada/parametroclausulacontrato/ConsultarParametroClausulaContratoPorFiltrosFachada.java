package com.inmocontrol.negocio.fachada.parametroclausulacontrato;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import java.util.List;

public interface ConsultarParametroClausulaContratoPorFiltrosFachada extends FachadaConRetorno<ParametroClausulaContratoDTO, List<ParametroClausulaContratoEntidad>> {
}