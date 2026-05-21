package com.inmocontrol.negocio.casouso.tipoparticipante;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import java.util.List;

public interface ConsultarTipoParticipantePorFiltrosCasoUso extends CasoUsoConRetorno<TipoParticipanteDominio, List<TipoParticipanteEntidad>> {
}