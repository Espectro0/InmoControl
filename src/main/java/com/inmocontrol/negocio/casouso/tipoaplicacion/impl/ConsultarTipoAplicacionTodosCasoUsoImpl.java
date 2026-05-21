package com.inmocontrol.negocio.casouso.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionTodosCasoUso;
import java.util.List;

public class ConsultarTipoAplicacionTodosCasoUsoImpl
    implements ConsultarTipoAplicacionTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoAplicacionTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<TipoAplicacionEntidad> ejecutar() {
    return daoFactory.obtenerTipoAplicacionDAO().consultarTodos();
  }
}
