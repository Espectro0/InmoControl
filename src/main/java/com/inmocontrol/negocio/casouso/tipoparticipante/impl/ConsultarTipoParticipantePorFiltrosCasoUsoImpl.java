package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipantePorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarTipoParticipantePorFiltrosCasoUsoImpl
    implements ConsultarTipoParticipantePorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoParticipantePorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<TipoParticipanteEntidad> ejecutar(TipoParticipanteDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerTipoParticipanteDAO().consultarTodos();
    }
    return daoFactory
        .obtenerTipoParticipanteDAO()
        .consultarPorFiltro(
            new TipoParticipanteEntidad.Builder().nombre(datos.getNombre()).build());
  }
}
