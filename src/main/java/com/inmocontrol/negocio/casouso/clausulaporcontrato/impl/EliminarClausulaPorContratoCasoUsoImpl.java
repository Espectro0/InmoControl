package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.EliminarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarClausulaPorContratoCasoUsoImpl implements EliminarClausulaPorContratoCasoUso {

  private DAOFactory daoFactory;

  public EliminarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ClausulaPorContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaClausulaPorContrato(datos);
    validarContratoNoCerrado(datos);
    validarIntegridadLegalMinima(datos);
    eliminarClausulaPorContrato(datos);
  }

  private void validarObligatoriedadId(ClausulaPorContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La clausula por contrato a eliminar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la clausula por contrato es obligatorio.");
    }
  }

  private void validarExistenciaClausulaPorContrato(ClausulaPorContratoDominio datos) {
    ClausulaPorContratoEntidad existente =
        daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion(
          "No existe una clausula por contrato con el ID: " + datos.getId());
    }
  }

  private void validarContratoNoCerrado(ClausulaPorContratoDominio datos) {
    ClausulaPorContratoEntidad existente =
        daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
    if (existente != null && existente.getContrato() != null) {
      ContratoEntidad contrato =
          daoFactory.obtenerContratoDAO().consultarPorId(existente.getContrato().getId());
      if (contrato != null && Boolean.FALSE.equals(contrato.getEsActivo())) {
        throw new ValidacionExcepcion(
            "No es posible eliminar la clausula porque el contrato esta cerrado o firmado.");
      }
    }
  }

  private void validarIntegridadLegalMinima(ClausulaPorContratoDominio datos) {
    ClausulaPorContratoEntidad existente =
        daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
    if (existente != null && existente.getContrato() != null) {
      ClausulaPorContratoEntidad filtro =
          new ClausulaPorContratoEntidad.Builder()
              .contrato(new ContratoEntidad.Builder().id(existente.getContrato().getId()).build())
              .build();
      var clausulas = daoFactory.obtenerClausulaPorContratoDAO().consultarPorFiltro(filtro);
      if (clausulas.size() <= 1) {
        throw new ValidacionExcepcion(
            "No es posible eliminar la clausula porque el contrato debe tener al menos una clausula.");
      }
    }
  }

  private void eliminarClausulaPorContrato(ClausulaPorContratoDominio datos) {
    daoFactory.obtenerClausulaPorContratoDAO().eliminar(datos.getId());
  }
}
