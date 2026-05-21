package com.inmocontrol.negocio.casouso.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoTodosCasoUso;
import java.util.List;

public class ConsultarTipoDocumentoTodosCasoUsoImpl implements ConsultarTipoDocumentoTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoDocumentoTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<TipoDocumentoEntidad> ejecutar() {
    return daoFactory.obtenerTipoDocumentoDAO().consultarTodos();
  }
}
