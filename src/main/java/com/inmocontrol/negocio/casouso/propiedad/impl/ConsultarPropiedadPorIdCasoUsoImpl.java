package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorIdCasoUso;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
      throw new ValidacionExcepcion("La propiedad a consultar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la propiedad es obligatorio.");
    }
  }

  private PropiedadEntidad consultarPropiedad(PropiedadDominio datos) {
    PropiedadEntidad propiedadEntidad =
        daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(propiedadEntidad)) {
      throw new ValidacionExcepcion("No existe una propiedad con el ID: " + datos.getId());
    }
    return propiedadEntidad;
  }
}
