package com.inmocontrol.negocio.casouso.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarCiudadPorIdCasoUsoImpl implements ConsultarCiudadPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarCiudadPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public CiudadEntidad ejecutar(CiudadDominio datos) {
    validarObligatoriedadIdCiudad(datos);
    return consultarCiudad(datos);
  }

  private void validarObligatoriedadIdCiudad(CiudadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La ciudad a consultar no es valida.",
          "Validacion fallida en ConsultarCiudadPorIdCasoUsoImpl.ejecutar() - datos nulos");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la ciudad es obligatorio.",
          "Validacion fallida en ConsultarCiudadPorIdCasoUsoImpl.ejecutar() - ID nulo");
    }
  }

  private CiudadEntidad consultarCiudad(CiudadDominio datos) {
    CiudadEntidad ciudadEntidad = daoFactory.obtenerCiudadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(ciudadEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe una ciudad con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarCiudadPorIdCasoUsoImpl.consultarCiudad() - Ciudad no encontrada");
    }
    return ciudadEntidad;
  }
}


