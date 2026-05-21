package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.TipoParticipanteDAO;
import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.negocio.casouso.tipoparticipante.EliminarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;

public class EliminarTipoParticipanteCasoUsoImpl implements EliminarTipoParticipanteCasoUso {

  private DAOFactory daoFactory;

  public EliminarTipoParticipanteCasoUsoImpl(DAOFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(TipoParticipanteDominio dominio) {
    TipoParticipanteDAO dao = daoFactory.obtenerTipoParticipanteDAO();
    dao.eliminar(dominio.getId());
  }
}
