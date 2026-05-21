package com.inmocontrol.negocio.fachada.participantecontrato;

import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.fachada.FachadaConRetorno;
import java.util.List;

public interface ConsultarParticipanteContratoPorFiltrosFachada
    extends FachadaConRetorno<ParticipanteContratoDTO, List<ParticipanteContratoEntidad>> {}
