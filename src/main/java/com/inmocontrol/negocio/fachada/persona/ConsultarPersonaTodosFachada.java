package com.inmocontrol.negocio.fachada.persona;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.entidad.PersonaEntidad;
import java.util.List;

public interface ConsultarPersonaTodosFachada extends FachadaConRetorno<Void, List<PersonaEntidad>> {
}