package com.inmocontrol.negocio.casouso.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarDepartamentoPorIdCasoUsoImpl implements ConsultarDepartamentoPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarDepartamentoPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public DepartamentoEntidad ejecutar(DepartamentoDominio datos) {
    validarObligatoriedadIdDepartamento(datos);
    return consultarDepartamento(datos);
  }

  private void validarObligatoriedadIdDepartamento(DepartamentoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El departamento a consultar no es valido.",
          "Validacion fallida en ConsultarDepartamentoPorIdCasoUsoImpl.validarObligatoriedadIdDepartamento() - El departamento a consultar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del departamento es obligatorio.",
          "Validacion fallida en ConsultarDepartamentoPorIdCasoUsoImpl.validarObligatoriedadIdDepartamento() - El ID del departamento es obligatorio."
      );
    }
  }

  private DepartamentoEntidad consultarDepartamento(DepartamentoDominio datos) {
    DepartamentoEntidad departamentoEntidad =
        daoFactory.obtenerDepartamentoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(departamentoEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe un departamento con el ID: " + datos.getId(),
          "Error en ConsultarDepartamentoPorIdCasoUsoImpl.consultarDepartamento() - No existe un departamento con el ID: " + datos.getId()
      );
    }
    return departamentoEntidad;
  }
}


