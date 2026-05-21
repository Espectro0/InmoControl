package com.inmocontrol.negocio.casouso.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadTodosCasoUso;
import java.util.List;

public class ConsultarTipoPropiedadTodosCasoUsoImpl implements ConsultarTipoPropiedadTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoPropiedadTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<TipoPropiedadEntidad> ejecutar() {
    return daoFactory.obtenerTipoPropiedadDAO().consultarTodos();
  }
}
