package com.inmocontrol.negocio.casouso.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarTipoAplicacionPorIdCasoUsoImpl
    implements ConsultarTipoAplicacionPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoAplicacionPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public TipoAplicacionEntidad ejecutar(TipoAplicacionDominio datos) {
    validarObligatoriedadIdTipoAplicacion(datos);
    return consultarTipoAplicacion(datos);
  }

  private void validarObligatoriedadIdTipoAplicacion(TipoAplicacionDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El tipo de aplicacion a consultar no es valido.",
          "Validacion fallida en ConsultarTipoAplicacionPorIdCasoUsoImpl.validarObligatoriedadIdTipoAplicacion() - datos nulos");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del tipo de aplicacion es obligatorio.",
          "Validacion fallida en ConsultarTipoAplicacionPorIdCasoUsoImpl.validarObligatoriedadIdTipoAplicacion() - ID nulo");
    }
  }

  private TipoAplicacionEntidad consultarTipoAplicacion(TipoAplicacionDominio datos) {
    TipoAplicacionEntidad tipoAplicacionEntidad =
        daoFactory.obtenerTipoAplicacionDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(tipoAplicacionEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe un tipo de aplicacion con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarTipoAplicacionPorIdCasoUsoImpl.consultarTipoAplicacion() - Tipo aplicacion no encontrado");
    }
    return tipoAplicacionEntidad;
  }
}


