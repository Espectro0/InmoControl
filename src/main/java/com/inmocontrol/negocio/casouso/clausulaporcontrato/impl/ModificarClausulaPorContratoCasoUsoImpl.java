package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ModificarClausulaPorContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilValidacion;


public class ModificarClausulaPorContratoCasoUsoImpl
    implements ModificarClausulaPorContratoCasoUso {

  private DAOFactory daoFactory;

  public ModificarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ClausulaPorContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaClausulaPorContrato(datos);
    validarFormatos(datos);
    validarContratoNoCerrado(datos);
    modificarClausulaPorContrato(datos);
  }

  private void validarObligatoriedadId(ClausulaPorContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La clausula por contrato a modificar no es valida.",
          "Validacion fallida en ModificarClausulaPorContratoCasoUsoImpl.validarObligatoriedadId() - La clausula por contrato a modificar no es valida."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la clausula por contrato es obligatorio.",
          "Validacion fallida en ModificarClausulaPorContratoCasoUsoImpl.validarObligatoriedadId() - El ID de la clausula por contrato es obligatorio."
      );
    }
  }

  private void validarExistenciaClausulaPorContrato(ClausulaPorContratoDominio datos) {
    ClausulaPorContratoEntidad existente =
        daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe una clausula por contrato con el ID: " + datos.getId(),
          "Error en ModificarClausulaPorContratoCasoUsoImpl.validarExistenciaClausulaPorContrato() - No existe una clausula por contrato con el ID: " + datos.getId()
      );
    }
  }

  private void validarFormatos(ClausulaPorContratoDominio datos) {
    if (datos.getNumeroClausula() != null
        && !UtilValidacion.validarRangoEntero(datos.getNumeroClausula(), 1, 40)) {
      throw new InmocontrolExcepcion(
          "El numero de clausula debe estar entre 1 y 40.",
          "Validacion fallida en ModificarClausulaPorContratoCasoUsoImpl.validarFormatos() - El numero de clausula debe estar entre 1 y 40."
      );
    }
  }

  private void validarContratoNoCerrado(ClausulaPorContratoDominio datos) {
    ClausulaPorContratoEntidad existente =
        daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
    if (existente != null && existente.getContrato() != null) {
      ContratoEntidad contrato =
          daoFactory.obtenerContratoDAO().consultarPorId(existente.getContrato().getId());
      if (contrato != null && Boolean.FALSE.equals(contrato.getEsActivo())) {
        throw new InmocontrolExcepcion(
            "No es posible modificar la clausula porque el contrato esta cerrado o firmado.",
            "Validacion fallida en ModificarClausulaPorContratoCasoUsoImpl.validarContratoNoCerrado() - No es posible modificar la clausula porque el contrato esta cerrado o firmado."
        );
      }
    }
  }

  private void modificarClausulaPorContrato(ClausulaPorContratoDominio dominio) {
    ClausulaPorContratoEntidad entidad =
        new ClausulaPorContratoEntidad.Builder()
            .id(dominio.getId())
            .numeroClausula(dominio.getNumeroClausula())
            .contrato(
            		dominio.getContrato() != null
                    ? new ContratoEntidad.Builder().id(dominio.getContrato().getId()).build()
                    : null)
            .clausula(
            		dominio.getClausula() != null
                    ? new ClausulaContratoEntidad.Builder().id(dominio.getClausula().getId()).build()
                    : null)
            .build();
    daoFactory.obtenerClausulaPorContratoDAO().actualizar(entidad.getId(), entidad);
  }
}


