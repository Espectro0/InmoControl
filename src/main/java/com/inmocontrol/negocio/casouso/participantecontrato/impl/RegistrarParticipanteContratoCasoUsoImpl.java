package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.RegistrarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarParticipanteContratoCasoUsoImpl
    implements RegistrarParticipanteContratoCasoUso {

  private DAOFactory daoFactory;

  public RegistrarParticipanteContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParticipanteContratoDominio datos) {
    validarObligatoriedadCampos(datos);
    registrarParticipanteContrato(datos);
  }

  private void validarObligatoriedadCampos(ParticipanteContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El participante contrato a registrar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getPersona()) || UtilObjeto.esNulo(datos.getPersona().getId())) {
      throw new ValidacionExcepcion("La persona es obligatoria.");
    }
    if (UtilObjeto.esNulo(datos.getTipoParticipante())
        || UtilObjeto.esNulo(datos.getTipoParticipante().getId())) {
      throw new ValidacionExcepcion("El tipo de participante es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getContrato()) || UtilObjeto.esNulo(datos.getContrato().getId())) {
      throw new ValidacionExcepcion("El contrato es obligatorio.");
    }
  }

  private void registrarParticipanteContrato(ParticipanteContratoDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerParticipanteContratoDAO().consultarPorId(uuid) != null);
    ParticipanteContratoEntidad entidad =
        new ParticipanteContratoEntidad.Builder()
            .id(idUnico)
            .persona(new PersonaEntidad.Builder().id(datos.getPersona().getId()).build())
            .tipoParticipante(
                new TipoParticipanteEntidad.Builder()
                    .id(datos.getTipoParticipante().getId())
                    .build())
            .contrato(new ContratoEntidad.Builder().id(datos.getContrato().getId()).build())
            .build();
    daoFactory.obtenerParticipanteContratoDAO().crear(entidad);
  }
}
