package com.inmocontrol.negocio.fachada.tipodocumento;

import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarTipoDocumentoPorFiltrosFachada
    extends FachadaConRetorno<TipoDocumentoDTO, List<TipoDocumentoEntidad>> {}
