package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ActivarContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ActivarContratoCasoUsoImpl implements ActivarContratoCasoUso {

  private DAOFactory daoFactory;

  public ActivarContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaContrato(datos);
    validarQueEstaSuspendido(datos);
    validarTieneClausulas(datos);
    activarContrato(datos);
  }

  private void validarObligatoriedadId(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El contrato a activar no es valido.",
          "Validacion fallida en ActivarContratoCasoUsoImpl.validarObligatoriedadId() - El contrato a activar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del contrato es obligatorio.",
          "Validacion fallida en ActivarContratoCasoUsoImpl.validarObligatoriedadId() - El ID del contrato es obligatorio."
      );
    }
  }

  private void validarExistenciaContrato(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un contrato con el ID: " + datos.getId(),
          "Error en ActivarContratoCasoUsoImpl.validarExistenciaContrato() - No existe un contrato con el ID: " + datos.getId()
      );
    }
  }

  private void validarQueEstaSuspendido(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    if (Boolean.TRUE.equals(existente.getEsActivo())) {
      throw new InmocontrolExcepcion(
          "El contrato ya se encuentra activo.",
          "Validacion fallida en ActivarContratoCasoUsoImpl.validarQueEstaSuspendido() - El contrato ya se encuentra activo."
      );
    }
  }

  private void validarTieneClausulas(ContratoDominio datos) {
    ClausulaPorContratoEntidad filtro =
        new ClausulaPorContratoEntidad.Builder()
            .contrato(new ContratoEntidad.Builder().id(datos.getId()).build())
            .build();
    var clausulas = daoFactory.obtenerClausulaPorContratoDAO().consultarPorFiltro(filtro);
    if (clausulas.isEmpty()) {
      throw new InmocontrolExcepcion(
          "El contrato debe tener al menos una clausula para poder ser activado.",
          "Validacion fallida en ActivarContratoCasoUsoImpl.validarTieneClausulas() - El contrato debe tener al menos una clausula para poder ser activado."
      );
    }
  }

  private void activarContrato(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    ContratoEntidad entidad =
        new ContratoEntidad.Builder()
            .id(existente.getId())
            .codigoContrato(existente.getCodigoContrato())
            .fechaInicio(existente.getFechaInicio())
            .fechaFin(existente.getFechaFin())
            .esActivo(true)
            .propiedad(existente.getPropiedad())
            .build();
    daoFactory.obtenerContratoDAO().actualizar(entidad.getId(), entidad);
  }
}


