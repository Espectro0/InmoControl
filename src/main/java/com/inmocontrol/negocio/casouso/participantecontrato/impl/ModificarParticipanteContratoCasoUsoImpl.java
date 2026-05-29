package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ModificarParticipanteContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ModificarParticipanteContratoCasoUsoImpl
    implements ModificarParticipanteContratoCasoUso {

  private DAOFactory daoFactory;

  public ModificarParticipanteContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParticipanteContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaParticipanteContrato(datos);
    modificarParticipanteContrato(datos);
  }

  private void validarObligatoriedadId(ParticipanteContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El participante contrato a modificar no es valido.",
          "Validacion fallida en ModificarParticipanteContratoCasoUsoImpl.validarObligatoriedadId() - El participante contrato a modificar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del participante contrato es obligatorio.",
          "Validacion fallida en ModificarParticipanteContratoCasoUsoImpl.validarObligatoriedadId() - El ID del participante contrato es obligatorio."
      );
    }
    if (datos.getPersona() != null && datos.getPersona().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("La persona es obligatoria");
    }
    if (datos.getTipoParticipante() != null && datos.getTipoParticipante().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El tipo de participante es obligatorio");
    }
    if (datos.getContrato() != null && datos.getContrato().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El contrato es obligatorio");
    }
  }

  private void validarExistenciaParticipanteContrato(ParticipanteContratoDominio datos) {
    ParticipanteContratoEntidad existente =
        daoFactory.obtenerParticipanteContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un participante contrato con el ID: " + datos.getId(),
          "Validacion fallida en ModificarParticipanteContratoCasoUsoImpl - No encontrado con ID: " + datos.getId()
      );
    }
  }

  private void modificarParticipanteContrato(ParticipanteContratoDominio datos) {
    ParticipanteContratoEntidad entidad =
        new ParticipanteContratoEntidad.Builder()
            .id(datos.getId())
            .persona(
                datos.getPersona() != null
                    ? new PersonaEntidad.Builder().id(datos.getPersona().getId()).build()
                    : null)
            .tipoParticipante(
                datos.getTipoParticipante() != null
                    ? new TipoParticipanteEntidad.Builder()
                        .id(datos.getTipoParticipante().getId())
                        .build()
                    : null)
            .contrato(
                datos.getContrato() != null
                    ? new ContratoEntidad.Builder().id(datos.getContrato().getId()).build()
                    : null)
            .build();
    daoFactory.obtenerParticipanteContratoDAO().actualizar(entidad.getId(), entidad);
  }
}


