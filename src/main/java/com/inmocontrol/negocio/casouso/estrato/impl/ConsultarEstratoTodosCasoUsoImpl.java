package com.inmocontrol.negocio.casouso.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoTodosCasoUso;
import java.util.List;

public class ConsultarEstratoTodosCasoUsoImpl implements ConsultarEstratoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarEstratoTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<EstratoEntidad> ejecutar() {
    return daoFactory.obtenerEstratoDAO().consultarTodos();
  }
}
