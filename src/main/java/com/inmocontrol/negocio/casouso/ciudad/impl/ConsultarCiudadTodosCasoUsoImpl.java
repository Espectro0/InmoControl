package com.inmocontrol.negocio.casouso.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadTodosCasoUso;
import java.util.List;

public class ConsultarCiudadTodosCasoUsoImpl implements ConsultarCiudadTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarCiudadTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<CiudadEntidad> ejecutar() {
    return daoFactory.obtenerCiudadDAO().consultarTodos();
  }
}
