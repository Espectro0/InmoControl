package com.inmocontrol.negocio.fachada.tipoparticipante;

import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import java.util.List;

public interface ConsultarTipoParticipantePorFiltrosFachada extends FachadaConRetorno<TipoParticipanteDTO, List<TipoParticipanteEntidad>> {
}