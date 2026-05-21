package com.inmocontrol.negocio.fachada.tipopropiedad;

import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarTipoPropiedadPorFiltrosFachada
    extends FachadaConRetorno<TipoPropiedadDTO, List<TipoPropiedadEntidad>> {}
