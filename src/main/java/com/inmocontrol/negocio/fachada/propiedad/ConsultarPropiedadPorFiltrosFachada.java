package com.inmocontrol.negocio.fachada.propiedad;

import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarPropiedadPorFiltrosFachada
    extends FachadaConRetorno<PropiedadDTO, List<PropiedadEntidad>> {}
