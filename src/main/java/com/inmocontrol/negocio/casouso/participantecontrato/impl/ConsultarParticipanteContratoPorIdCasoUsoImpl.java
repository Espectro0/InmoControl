package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarParticipanteContratoPorIdCasoUsoImpl
    implements ConsultarParticipanteContratoPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParticipanteContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public ParticipanteContratoEntidad ejecutar(ParticipanteContratoDominio datos) {
    validarObligatoriedadIdParticipanteContrato(datos);
    return consultarParticipanteContrato(datos);
  }

  private void validarObligatoriedadIdParticipanteContrato(ParticipanteContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El participante contrato a consultar no es valido.",
          "Validacion fallida en ConsultarParticipanteContratoPorIdCasoUsoImpl.validarObligatoriedadIdParticipanteContrato() - El participante contrato a consultar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del participante contrato es obligatorio.",
          "Validacion fallida en ConsultarParticipanteContratoPorIdCasoUsoImpl.validarObligatoriedadIdParticipanteContrato() - El ID del participante contrato es obligatorio."
      );
    }
  }

  private ParticipanteContratoEntidad consultarParticipanteContrato(
      ParticipanteContratoDominio datos) {
    ParticipanteContratoEntidad entidad =
        daoFactory.obtenerParticipanteContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(entidad)) {
      throw new InmocontrolExcepcion(
          "No existe un participante contrato con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarParticipanteContratoPorIdCasoUsoImpl - No encontrado con ID: " + datos.getId()
      );
    }
    return entidad;
  }
}


