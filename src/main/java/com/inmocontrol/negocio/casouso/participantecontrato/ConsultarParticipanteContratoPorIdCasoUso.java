package com.inmocontrol.negocio.casouso.participantecontrato;

import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;

public interface ConsultarParticipanteContratoPorIdCasoUso
    extends CasoUsoConRetorno<ParticipanteContratoDominio, ParticipanteContratoEntidad> {}
