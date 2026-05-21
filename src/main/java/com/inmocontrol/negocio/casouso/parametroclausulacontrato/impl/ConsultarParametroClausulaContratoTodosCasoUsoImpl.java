package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoTodosCasoUso;
import java.util.List;

public class ConsultarParametroClausulaContratoTodosCasoUsoImpl
    implements ConsultarParametroClausulaContratoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParametroClausulaContratoTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ParametroClausulaContratoEntidad> ejecutar() {
    return daoFactory.obtenerParametroClausulaContratoDAO().consultarTodos();
  }
}
