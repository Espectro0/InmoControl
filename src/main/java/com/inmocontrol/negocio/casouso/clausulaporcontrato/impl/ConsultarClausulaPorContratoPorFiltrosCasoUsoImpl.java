package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarClausulaPorContratoPorFiltrosCasoUsoImpl
    implements ConsultarClausulaPorContratoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarClausulaPorContratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ClausulaPorContratoEntidad> ejecutar(ClausulaPorContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerClausulaPorContratoDAO().consultarTodos();
    }
    return daoFactory
        .obtenerClausulaPorContratoDAO()
        .consultarPorFiltro(
            new ClausulaPorContratoEntidad.Builder()
                .numeroClausula(datos.getNumeroClausula())
                .build());
  }
}
