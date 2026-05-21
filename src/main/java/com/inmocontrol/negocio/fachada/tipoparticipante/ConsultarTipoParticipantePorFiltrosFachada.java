package com.inmocontrol.negocio.fachada.tipoparticipante;

import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarTipoParticipantePorFiltrosFachada
    extends FachadaConRetorno<TipoParticipanteDTO, List<TipoParticipanteEntidad>> {}
