package com.inmocontrol.negocio.casouso.tipoparticipante;

import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;

public interface ConsultarTipoParticipantePorIdCasoUso
    extends CasoUsoConRetorno<TipoParticipanteDominio, TipoParticipanteEntidad> {}
