package com.inmocontrol.negocio.casouso.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarPaisPorFiltrosCasoUsoImpl implements ConsultarPaisPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPaisPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<PaisEntidad> ejecutar(PaisDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerPaisDAO().consultarTodos();
    }
    return daoFactory
        .obtenerPaisDAO()
        .consultarPorFiltro(new PaisEntidad.Builder().nombre(datos.getNombre()).build());
  }
}
