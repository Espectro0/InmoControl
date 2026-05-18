package com.inmocontrol.negocio.fachada.pais;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.PaisEntidad;
import java.util.List;

public interface ConsultarPaisPorFiltrosFachada extends FachadaConRetorno<PaisDTO, List<PaisEntidad>> {
}