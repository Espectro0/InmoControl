package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.EliminarParametroCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarParametroCasoUsoImpl implements EliminarParametroCasoUso {

  private DAOFactory daoFactory;

  public EliminarParametroCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParametroDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaParametro(datos);
    eliminarParametro(datos);
  }

  private void validarObligatoriedadId(ParametroDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El parametro a eliminar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del parametro es obligatorio.");
    }
  }

  private void validarExistenciaParametro(ParametroDominio datos) {
    ParametroEntidad existente = daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe un parametro con el ID: " + datos.getId());
    }
  }

  private void eliminarParametro(ParametroDominio datos) {
    daoFactory.obtenerParametroDAO().eliminar(datos.getId());
  }
}
