package com.inmocontrol.negocio.fachada.tipopropiedad;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import java.util.List;

public interface ConsultarTipoPropiedadPorFiltrosFachada extends FachadaConRetorno<TipoPropiedadDTO, List<TipoPropiedadEntidad>> {
}