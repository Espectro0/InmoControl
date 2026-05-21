package com.inmocontrol.negocio.casouso.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisTodosCasoUso;
import java.util.List;

public class ConsultarPaisTodosCasoUsoImpl implements ConsultarPaisTodosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPaisTodosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<PaisEntidad> ejecutar() {
    return daoFactory.obtenerPaisDAO().consultarTodos();
  }
}
