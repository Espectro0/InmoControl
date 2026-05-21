package com.inmocontrol.negocio.fachada.parametroclausulacontrato;

import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarParametroClausulaContratoPorFiltrosFachada
    extends FachadaConRetorno<
        ParametroClausulaContratoDTO, List<ParametroClausulaContratoEntidad>> {}
