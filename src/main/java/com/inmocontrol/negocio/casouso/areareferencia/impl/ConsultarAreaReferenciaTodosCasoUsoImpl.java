package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaTodosCasoUso;
import java.util.List;

public class ConsultarAreaReferenciaTodosCasoUsoImpl
    implements ConsultarAreaReferenciaTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarAreaReferenciaTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<AreaReferenciaEntidad> ejecutar() {
    return daoFactory.obtenerAreaReferenciaDAO().consultarTodos();
  }
}
