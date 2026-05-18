package com.inmocontrol.negocio.fachada.ciudad;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.entidad.CiudadEntidad;
import java.util.List;

public interface ConsultarCiudadPorFiltrosFachada extends FachadaConRetorno<CiudadDTO, List<CiudadEntidad>> {
}