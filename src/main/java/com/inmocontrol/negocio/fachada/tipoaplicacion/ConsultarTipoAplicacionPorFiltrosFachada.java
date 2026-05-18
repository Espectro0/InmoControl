package com.inmocontrol.negocio.fachada.tipoaplicacion;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import java.util.List;

public interface ConsultarTipoAplicacionPorFiltrosFachada extends FachadaConRetorno<TipoAplicacionDTO, List<TipoAplicacionEntidad>> {
}