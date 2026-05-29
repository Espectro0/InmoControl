package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarParametroPorIdCasoUsoImpl implements ConsultarParametroPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParametroPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public ParametroEntidad ejecutar(ParametroDominio datos) {
    validarObligatoriedadIdParametro(datos);
    return consultarParametro(datos);
  }

  private void validarObligatoriedadIdParametro(ParametroDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El parametro a consultar no es valido.",
          "Validacion fallida en ConsultarParametroPorIdCasoUsoImpl.validarObligatoriedadIdParametro() - El parametro a consultar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del parametro es obligatorio.",
          "Validacion fallida en ConsultarParametroPorIdCasoUsoImpl.validarObligatoriedadIdParametro() - El ID del parametro es obligatorio."
      );
    }
  }

  private ParametroEntidad consultarParametro(ParametroDominio datos) {
    ParametroEntidad parametroEntidad =
        daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(parametroEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe un parametro con el ID: " + datos.getId(),
          "Error en ConsultarParametroPorIdCasoUsoImpl.consultarParametro() - No existe un parametro con el ID: " + datos.getId()
      );
    }
    return parametroEntidad;
  }
}


