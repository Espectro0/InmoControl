package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarAreaReferenciaPorFiltrosCasoUsoImpl
    implements ConsultarAreaReferenciaPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarAreaReferenciaPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<AreaReferenciaEntidad> ejecutar(AreaReferenciaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerAreaReferenciaDAO().consultarTodos();
    }
    return daoFactory
        .obtenerAreaReferenciaDAO()
        .consultarPorFiltro(new AreaReferenciaEntidad.Builder().nombre(datos.getNombre()).build());
  }
}
