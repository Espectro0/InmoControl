package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroTodosCasoUso;
import java.util.List;

public class ConsultarParametroTodosCasoUsoImpl implements ConsultarParametroTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParametroTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ParametroEntidad> ejecutar() {
    return daoFactory.obtenerParametroDAO().consultarTodos();
  }
}
