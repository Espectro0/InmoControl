package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.SuspenderContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class SuspenderContratoCasoUsoImpl implements SuspenderContratoCasoUso {

  private DAOFactory daoFactory;

  public SuspenderContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaContrato(datos);
    validarQueEstaActivo(datos);
    suspenderContrato(datos);
  }

  private void validarObligatoriedadId(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El contrato a suspender no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del contrato es obligatorio.");
    }
  }

  private void validarExistenciaContrato(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe un contrato con el ID: " + datos.getId());
    }
  }

  private void validarQueEstaActivo(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    if (!Boolean.TRUE.equals(existente.getEsActivo())) {
      throw new ValidacionExcepcion("El contrato ya se encuentra suspendido.");
    }
  }

  private void suspenderContrato(ContratoDominio datos) {
    ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    ContratoEntidad entidad =
        new ContratoEntidad.Builder()
            .id(existente.getId())
            .codigoContrato(existente.getCodigoContrato())
            .fechaInicio(existente.getFechaInicio())
            .fechaFin(existente.getFechaFin())
            .esActivo(false)
            .propiedad(existente.getPropiedad())
            .build();
    daoFactory.obtenerContratoDAO().actualizar(entidad.getId(), entidad);
  }
}
