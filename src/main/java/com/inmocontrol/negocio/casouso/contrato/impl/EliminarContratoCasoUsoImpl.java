package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.negocio.casouso.contrato.EliminarContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class EliminarContratoCasoUsoImpl implements EliminarContratoCasoUso {

  private DAOFactory daoFactory;

  public EliminarContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaContrato(datos);
    eliminarContrato(datos);
  }

  private void validarObligatoriedadId(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El contrato a eliminar no es valido.",
          "Validacion fallida en EliminarContratoCasoUsoImpl.validarObligatoriedadId() - El contrato a eliminar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del contrato es obligatorio.",
          "Validacion fallida en EliminarContratoCasoUsoImpl.validarObligatoriedadId() - El ID del contrato es obligatorio."
      );
    }
  }

  private void validarExistenciaContrato(ContratoDominio datos) {
    com.inmocontrol.entidad.ContratoEntidad existente =
        daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un contrato con el ID: " + datos.getId(),
          "Error en EliminarContratoCasoUsoImpl.validarExistenciaContrato() - No existe un contrato con el ID: " + datos.getId()
      );
    }
  }

  private void eliminarContrato(ContratoDominio datos) {
    daoFactory.obtenerContratoDAO().eliminar(datos.getId());
  }
}


