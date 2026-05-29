package com.inmocontrol.negocio.casouso.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarEstratoPorIdCasoUsoImpl implements ConsultarEstratoPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarEstratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public EstratoEntidad ejecutar(EstratoDominio datos) {
    validarObligatoriedadIdEstrato(datos);
    return consultarEstrato(datos);
  }

  private void validarObligatoriedadIdEstrato(EstratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El estrato a consultar no es valido.",
          "Validacion fallida en ConsultarEstratoPorIdCasoUsoImpl.validarObligatoriedadIdEstrato() - El estrato a consultar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del estrato es obligatorio.",
          "Validacion fallida en ConsultarEstratoPorIdCasoUsoImpl.validarObligatoriedadIdEstrato() - El ID del estrato es obligatorio."
      );
    }
  }

  private EstratoEntidad consultarEstrato(EstratoDominio datos) {
    EstratoEntidad estratoEntidad = daoFactory.obtenerEstratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(estratoEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe un estrato con el ID: " + datos.getId(),
          "Error en ConsultarEstratoPorIdCasoUsoImpl.consultarEstrato() - No existe un estrato con el ID: " + datos.getId()
      );
    }
    return estratoEntidad;
  }
}


