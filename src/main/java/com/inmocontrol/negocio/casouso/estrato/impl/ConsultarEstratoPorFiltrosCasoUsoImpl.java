package com.inmocontrol.negocio.casouso.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarEstratoPorFiltrosCasoUsoImpl implements ConsultarEstratoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarEstratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<EstratoEntidad> ejecutar(EstratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerEstratoDAO().consultarTodos();
    }
    return daoFactory
        .obtenerEstratoDAO()
        .consultarPorFiltro(new EstratoEntidad.Builder().nombre(datos.getNombre()).build());
  }
}
