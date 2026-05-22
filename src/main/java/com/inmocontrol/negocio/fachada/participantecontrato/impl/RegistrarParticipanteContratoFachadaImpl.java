package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.negocio.casouso.participantecontrato.RegistrarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.RegistrarParticipanteContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.RegistrarParticipanteContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
			throw new ValidacionExcepcion("Los datos del participante contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParticipanteContratoDominio dominio = new ParticipanteContratoDominio.Builder()
					.persona(datos.getPersona() != null
							? new PersonaDominio.Builder().id(datos.getPersona().getId()).build()
							: null)
					.tipoParticipante(datos.getTipoParticipante() != null
							? new TipoParticipanteDominio.Builder().id(datos.getTipoParticipante().getId()).build()
							: null)
					.contrato(datos.getContrato() != null
							? new ContratoDominio.Builder().id(datos.getContrato().getId()).build()
							: null)
					.build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error registrando el participante contrato", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
