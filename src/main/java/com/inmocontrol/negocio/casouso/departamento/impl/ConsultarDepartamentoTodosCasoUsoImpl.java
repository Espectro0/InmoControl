package com.inmocontrol.negocio.casouso.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoTodosCasoUso;
import java.util.List;

public class ConsultarDepartamentoTodosCasoUsoImpl implements ConsultarDepartamentoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarDepartamentoTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<DepartamentoEntidad> ejecutar() {
    return daoFactory.obtenerDepartamentoDAO().consultarTodos();
  }
}
