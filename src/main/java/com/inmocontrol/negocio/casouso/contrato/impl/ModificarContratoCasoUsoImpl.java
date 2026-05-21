package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.contrato.ModificarContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarContratoCasoUsoImpl implements ModificarContratoCasoUso {

  private DAOFactory daoFactory;

  public ModificarContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaContrato(datos);
    modificarContrato(datos);
  }

  private void validarObligatoriedadId(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El contrato a modificar no es valido.");
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

  private void modificarContrato(ContratoDominio datos) {
    ContratoEntidad entidad =
        new ContratoEntidad.Builder()
            .id(datos.getId())
            .codigoContrato(datos.getCodigoContrato())
            .fechaInicio(datos.getFechaInicio())
            .fechaFin(datos.getFechaFin())
            .esActivo(datos.getEsActivo())
            .propiedad(
                datos.getPropiedad() != null
                    ? new PropiedadEntidad.Builder().id(datos.getPropiedad().getId()).build()
                    : null)
            .build();
    daoFactory.obtenerContratoDAO().actualizar(entidad.getId(), entidad);
  }
}
