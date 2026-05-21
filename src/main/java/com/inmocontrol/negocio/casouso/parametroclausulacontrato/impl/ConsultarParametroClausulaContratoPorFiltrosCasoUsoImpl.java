package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarParametroClausulaContratoPorFiltrosCasoUsoImpl
    implements ConsultarParametroClausulaContratoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParametroClausulaContratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ParametroClausulaContratoEntidad> ejecutar(ParametroClausulaContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerParametroClausulaContratoDAO().consultarTodos();
    }
    return daoFactory
        .obtenerParametroClausulaContratoDAO()
        .consultarPorFiltro(
            new ParametroClausulaContratoEntidad.Builder().valor(datos.getValor()).build());
  }
}
