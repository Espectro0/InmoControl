package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.EliminarPropiedadCasoUso;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarPropiedadCasoUsoImpl implements EliminarPropiedadCasoUso {

  private DAOFactory daoFactory;

  public EliminarPropiedadCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PropiedadDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaPropiedad(datos);
    eliminarPropiedad(datos);
  }

  private void validarObligatoriedadId(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La propiedad a eliminar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la propiedad es obligatorio.");
    }
  }

  private void validarExistenciaPropiedad(PropiedadDominio datos) {
    PropiedadEntidad existente = daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe una propiedad con el ID: " + datos.getId());
    }
  }

  private void eliminarPropiedad(PropiedadDominio datos) {
    daoFactory.obtenerPropiedadDAO().eliminar(datos.getId());
  }
}
