package com.inmocontrol.negocio.fachada.clausulaporcontrato;

import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarClausulaPorContratoPorFiltrosFachada
    extends FachadaConRetorno<ClausulaPorContratoDTO, List<ClausulaPorContratoEntidad>> {}
