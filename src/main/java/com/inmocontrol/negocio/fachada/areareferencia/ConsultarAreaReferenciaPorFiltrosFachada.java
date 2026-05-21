package com.inmocontrol.negocio.fachada.areareferencia;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import java.util.List;

public interface ConsultarAreaReferenciaPorFiltrosFachada extends FachadaConRetorno<AreaReferenciaDTO, List<AreaReferenciaEntidad>> {
}