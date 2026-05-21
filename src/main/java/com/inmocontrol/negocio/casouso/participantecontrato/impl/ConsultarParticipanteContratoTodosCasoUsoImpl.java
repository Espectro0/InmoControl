package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoTodosCasoUso;
import java.util.List;

public class ConsultarParticipanteContratoTodosCasoUsoImpl
    implements ConsultarParticipanteContratoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParticipanteContratoTodosCasoUsoImpl(DAOFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ParticipanteContratoEntidad> ejecutar() {
    return daoFactory.obtenerParticipanteContratoDAO().consultarTodos();
  }
}
