package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.EliminarParticipanteContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class EliminarParticipanteContratoCasoUsoImpl
    implements EliminarParticipanteContratoCasoUso {

  private DAOFactory daoFactory;

  public EliminarParticipanteContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParticipanteContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaParticipanteContrato(datos);
    eliminarParticipanteContrato(datos);
  }

  private void validarObligatoriedadId(ParticipanteContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El participante contrato a eliminar no es valido.",
          "Validacion fallida en EliminarParticipanteContratoCasoUsoImpl.validarObligatoriedadId() - El participante contrato a eliminar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del participante contrato es obligatorio.",
          "Validacion fallida en EliminarParticipanteContratoCasoUsoImpl.validarObligatoriedadId() - El ID del participante contrato es obligatorio."
      );
    }
  }

  private void validarExistenciaParticipanteContrato(ParticipanteContratoDominio datos) {
    ParticipanteContratoEntidad existente =
        daoFactory.obtenerParticipanteContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un participante contrato con el ID: " + datos.getId(),
          "Validacion fallida en EliminarParticipanteContratoCasoUsoImpl - No encontrado con ID: " + datos.getId()
      );
    }
  }

  private void eliminarParticipanteContrato(ParticipanteContratoDominio datos) {
    daoFactory.obtenerParticipanteContratoDAO().eliminar(datos.getId());
  }
}


