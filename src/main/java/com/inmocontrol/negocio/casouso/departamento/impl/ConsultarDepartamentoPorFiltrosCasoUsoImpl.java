package com.inmocontrol.negocio.casouso.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;
import java.util.List;

public class ConsultarDepartamentoPorFiltrosCasoUsoImpl
    implements ConsultarDepartamentoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarDepartamentoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<DepartamentoEntidad> ejecutar(DepartamentoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerDepartamentoDAO().consultarTodos();
    }
    if (datos.getPais().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El país es obligatorio");
    }
    if (datos.getPais().getNombre() != null && datos.getPais().getNombre().equals("N/A")) {
      throw new ValidadorExcepcion("El país es obligatorio");
    }
    return daoFactory
        .obtenerDepartamentoDAO()
        .consultarPorFiltro(
            new DepartamentoEntidad.Builder()
                .nombre(datos.getNombre())
                .pais(
                    datos.getPais() != null
                        ? new PaisEntidad.Builder().id(datos.getPais().getId()).build()
                        : null)
                .build());
  }
}
