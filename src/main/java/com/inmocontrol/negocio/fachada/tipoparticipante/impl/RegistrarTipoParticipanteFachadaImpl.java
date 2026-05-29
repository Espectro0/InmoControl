package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.casouso.tipoparticipante.RegistrarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.RegistrarTipoParticipanteCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.RegistrarTipoParticipanteFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

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
			throw new InmocontrolExcepcion("Los datos del tipo de participante no pueden ser nulos",
					"Validacion fallida en RegistrarTipoParticipanteFachadaImpl.ejecutar() - Los datos del tipo de participante no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder().nombre(datos.getNombre()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarTipoParticipanteFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
