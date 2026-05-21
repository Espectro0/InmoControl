package com.inmocontrol.negocio.casouso.tipoparticipante;

import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import java.util.List;

public interface ConsultarTipoParticipantePorFiltrosCasoUso
    extends CasoUsoConRetorno<TipoParticipanteDominio, List<TipoParticipanteEntidad>> {}
