package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.EliminarParametroCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;


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
      throw new InmocontrolExcepcion(
          "El parametro a eliminar no es valido.",
          "Validacion fallida en EliminarParametroCasoUsoImpl.validarObligatoriedadId() - El parametro a eliminar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del parametro es obligatorio.",
          "Validacion fallida en EliminarParametroCasoUsoImpl.validarObligatoriedadId() - El ID del parametro es obligatorio."
      );
    }
  }

  private void validarExistenciaParametro(ParametroDominio datos) {
    ParametroEntidad existente = daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un parametro con el ID: " + datos.getId(),
          "Error en EliminarParametroCasoUsoImpl.validarExistenciaParametro() - No existe un parametro con el ID: " + datos.getId()
      );
    }
  }

  private void eliminarParametro(ParametroDominio datos) {
    daoFactory.obtenerParametroDAO().eliminar(datos.getId());
  }
}


