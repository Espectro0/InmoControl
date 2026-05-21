package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoTodosCasoUso;
import java.util.List;

public class ConsultarClausulaContratoTodosCasoUsoImpl
    implements ConsultarClausulaContratoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarClausulaContratoTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ClausulaContratoEntidad> ejecutar() {
    return daoFactory.obtenerClausulaContratoDAO().consultarTodos();
  }
}
