package com.inmocontrol.negocio.fachada.propiedad;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import java.util.List;

public interface ConsultarPropiedadPorFiltrosFachada extends FachadaConRetorno<PropiedadDTO, List<PropiedadEntidad>> {
}