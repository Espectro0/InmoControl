package com.inmocontrol.negocio.casouso.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarCiudadPorFiltrosCasoUsoImpl implements ConsultarCiudadPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarCiudadPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<CiudadEntidad> ejecutar(CiudadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerCiudadDAO().consultarTodos();
    }
    return daoFactory
        .obtenerCiudadDAO()
        .consultarPorFiltro(
            new CiudadEntidad.Builder()
                .nombre(datos.getNombre())
                .departamento(
                    datos.getDepartamento() != null
                        ? new DepartamentoEntidad.Builder()
                            .id(datos.getDepartamento().getId())
                            .pais(
                                datos.getDepartamento().getPais() != null
                                    ? new PaisEntidad.Builder()
                                        .id(datos.getDepartamento().getPais().getId())
                                        .build()
                                    : null)
                            .build()
                        : null)
                .build());
  }
}
