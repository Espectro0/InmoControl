package com.inmocontrol.negocio.fachada.persona;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.entidad.PersonaEntidad;
import java.util.List;

public interface ConsultarPersonaPorFiltrosFachada extends FachadaConRetorno<PersonaDTO, List<PersonaEntidad>> {
}