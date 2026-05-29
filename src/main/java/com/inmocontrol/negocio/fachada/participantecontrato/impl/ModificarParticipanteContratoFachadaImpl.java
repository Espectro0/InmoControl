package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.negocio.casouso.participantecontrato.ModificarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.ModificarParticipanteContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.ModificarParticipanteContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ModificarParticipanteContratoFachadaImpl implements ModificarParticipanteContratoFachada {

	private DAOFactory daoFactory;
	private ModificarParticipanteContratoCasoUso casoUso;

	public ModificarParticipanteContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarParticipanteContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParticipanteContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del participante contrato no pueden ser nulos",
					"Validacion fallida en ModificarParticipanteContratoFachadaImpl.ejecutar() - Los datos del participante contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParticipanteContratoDominio dominio = new ParticipanteContratoDominio.Builder().id(datos.getId())
					.persona(new PersonaDominio.Builder().id(datos.getPersona().getId()).build())
					.tipoParticipante(new TipoParticipanteDominio.Builder().id(datos.getTipoParticipante().getId()).build())
					.contrato(new ContratoDominio.Builder().id(datos.getContrato().getId()).build())
					.			 build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ModificarParticipanteContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
