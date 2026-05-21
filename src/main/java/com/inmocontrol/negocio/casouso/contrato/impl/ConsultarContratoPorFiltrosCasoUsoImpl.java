package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarContratoPorFiltrosCasoUsoImpl implements ConsultarContratoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarContratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ContratoEntidad> ejecutar(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerContratoDAO().consultarTodos();
    }
    return daoFactory
        .obtenerContratoDAO()
        .consultarPorFiltro(
            new ContratoEntidad.Builder()
                .codigoContrato(datos.getCodigoContrato())
                .esActivo(datos.getEsActivo())
                .build());
  }
}
