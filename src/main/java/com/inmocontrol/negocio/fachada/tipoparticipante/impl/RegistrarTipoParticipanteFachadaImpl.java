package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.casouso.tipoparticipante.RegistrarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.RegistrarTipoParticipanteCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.RegistrarTipoParticipanteFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarTipoParticipanteFachadaImpl implements RegistrarTipoParticipanteFachada {

	private DAOFactory daoFactory;
	private RegistrarTipoParticipanteCasoUso casoUso;

	public RegistrarTipoParticipanteFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarTipoParticipanteCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(TipoParticipanteDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("Los datos del tipo de participante no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder().nombre(datos.getNombre()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error registrando el tipo de participante", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
