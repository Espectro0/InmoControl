package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarParametroClausulaContratoPorIdCasoUsoImpl
    implements ConsultarParametroClausulaContratoPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParametroClausulaContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public ParametroClausulaContratoEntidad ejecutar(ParametroClausulaContratoDominio datos) {
    validarObligatoriedadIdParametroClausulaContrato(datos);
    return consultarParametroClausulaContrato(datos);
  }

  private void validarObligatoriedadIdParametroClausulaContrato(
      ParametroClausulaContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El parametro clausula contrato a consultar no es valido.",
          "Validacion fallida en ConsultarParametroClausulaContratoPorIdCasoUsoImpl.validarObligatoriedadIdParametroClausulaContrato() - El parametro clausula contrato a consultar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del parametro clausula contrato es obligatorio.",
          "Validacion fallida en ConsultarParametroClausulaContratoPorIdCasoUsoImpl.validarObligatoriedadIdParametroClausulaContrato() - El ID del parametro clausula contrato es obligatorio."
      );
    }
  }

  private ParametroClausulaContratoEntidad consultarParametroClausulaContrato(
      ParametroClausulaContratoDominio datos) {
    ParametroClausulaContratoEntidad entidad =
        daoFactory.obtenerParametroClausulaContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(entidad)) {
      throw new InmocontrolExcepcion(
          "No existe un parametro clausula contrato con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarParametroClausulaContratoPorIdCasoUsoImpl - No encontrado"
      );
    }
    return entidad;
  }
}


