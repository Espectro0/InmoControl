package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.RegistrarParticipanteContratoCasoUso;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public class RegistrarParticipanteContratoCasoUsoImpl implements RegistrarParticipanteContratoCasoUso {

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
			throw new InmocontrolExcepcion("El participante contrato a registrar no es valido.",
					"Validacion fallida en RegistrarParticipanteContratoCasoUsoImpl.validarObligatoriedadCampos() - El participante contrato a registrar no es valido.");
		}
		if (datos.getPersona().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("La persona es obligatoria");
		}
		if (datos.getTipoParticipante().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("El tipo de participante es obligatorio");
		}
		if (datos.getContrato().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("El contrato es obligatorio");
		}
	}

	private void registrarParticipanteContrato(ParticipanteContratoDominio datos) {
		var idUnico = UtilUUID
				.generarUnico(uuid -> daoFactory.obtenerParticipanteContratoDAO().consultarPorId(uuid) != null);
		ParticipanteContratoEntidad entidad = new ParticipanteContratoEntidad.Builder().id(idUnico)
				.persona(new PersonaEntidad.Builder().id(datos.getPersona().getId()).build())
				.tipoParticipante(new TipoParticipanteEntidad.Builder().id(datos.getTipoParticipante().getId()).build())
				.contrato(new ContratoEntidad.Builder().id(datos.getContrato().getId()).build()).build();
		daoFactory.obtenerParticipanteContratoDAO().crear(entidad);
	}
}
