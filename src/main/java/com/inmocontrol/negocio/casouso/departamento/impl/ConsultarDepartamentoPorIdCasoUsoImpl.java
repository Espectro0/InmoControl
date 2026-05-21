package com.inmocontrol.negocio.casouso.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
      throw new ValidacionExcepcion("El departamento a consultar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del departamento es obligatorio.");
    }
  }

  private DepartamentoEntidad consultarDepartamento(DepartamentoDominio datos) {
    DepartamentoEntidad departamentoEntidad =
        daoFactory.obtenerDepartamentoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(departamentoEntidad)) {
      throw new ValidacionExcepcion("No existe un departamento con el ID: " + datos.getId());
    }
    return departamentoEntidad;
  }
}
