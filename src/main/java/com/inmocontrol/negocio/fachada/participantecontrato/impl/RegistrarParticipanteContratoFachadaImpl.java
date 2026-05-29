package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.RegistrarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.RegistrarParticipanteContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.RegistrarParticipanteContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class RegistrarParticipanteContratoFachadaImpl implements RegistrarParticipanteContratoFachada {

	private DAOFactory daoFactory;
	private RegistrarParticipanteContratoCasoUso casoUso;

	public RegistrarParticipanteContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarParticipanteContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParticipanteContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del participante contrato no pueden ser nulos",
					"Validacion fallida en RegistrarParticipanteContratoFachadaImpl.ejecutar() - Los datos del participante contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			PersonaEntidad personaEntidad = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getPersonaId());
			if (personaEntidad == null) {
				throw new InmocontrolExcepcion("La persona no existe",
						"Validacion fallida en RegistrarParticipanteContratoFachadaImpl.ejecutar() - La persona no existe");
			}

			TipoParticipanteEntidad tipoParticipanteEntidad = daoFactory.obtenerTipoParticipanteDAO()
					.consultarPorId(datos.getTipoParticipanteId());
			if (tipoParticipanteEntidad == null) {
				throw new InmocontrolExcepcion("El tipo de participante no existe",
						"Validacion fallida en RegistrarParticipanteContratoFachadaImpl.ejecutar() - El tipo de participante no existe");
			}

			ContratoEntidad contratoEntidad = daoFactory.obtenerContratoDAO().consultarPorId(datos.getContratoId());
			if (contratoEntidad == null) {
				throw new InmocontrolExcepcion("El contrato no existe",
						"Validacion fallida en RegistrarParticipanteContratoFachadaImpl.ejecutar() - El contrato no existe");
			}

			 ParticipanteContratoDominio dominio = new ParticipanteContratoDominio.Builder()
					.persona(new PersonaDominio.Builder().id(personaEntidad.getId()).build())
					.tipoParticipante(new TipoParticipanteDominio.Builder().id(tipoParticipanteEntidad.getId()).build())
					.contrato(new ContratoDominio.Builder().id(contratoEntidad.getId()).build()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarParticipanteContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
