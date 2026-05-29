package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarPropiedadPorIdCasoUsoImpl implements ConsultarPropiedadPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPropiedadPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public PropiedadEntidad ejecutar(PropiedadDominio datos) {
    validarObligatoriedadIdPropiedad(datos);
    return consultarPropiedad(datos);
  }

  private void validarObligatoriedadIdPropiedad(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La propiedad a consultar no es valida.",
          "Validacion fallida en ConsultarPropiedadPorIdCasoUsoImpl.validarObligatoriedadIdPropiedad() - datos nulos");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la propiedad es obligatorio.",
          "Validacion fallida en ConsultarPropiedadPorIdCasoUsoImpl.validarObligatoriedadIdPropiedad() - ID nulo");
    }
  }

  private PropiedadEntidad consultarPropiedad(PropiedadDominio datos) {
    PropiedadEntidad propiedadEntidad =
        daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(propiedadEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe una propiedad con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarPropiedadPorIdCasoUsoImpl.consultarPropiedad() - Propiedad no encontrada");
    }
    return propiedadEntidad;
  }
}


