package com.inmocontrol.negocio.casouso.participantecontrato;

import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import java.util.List;

public interface ConsultarParticipanteContratoPorFiltrosCasoUso
    extends CasoUsoConRetorno<ParticipanteContratoDominio, List<ParticipanteContratoEntidad>> {}
