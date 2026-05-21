package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipanteTodosCasoUso;
import java.util.List;

public class ConsultarTipoParticipanteTodosCasoUsoImpl
    implements ConsultarTipoParticipanteTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoParticipanteTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<TipoParticipanteEntidad> ejecutar() {
    return daoFactory.obtenerTipoParticipanteDAO().consultarTodos();
  }
}
