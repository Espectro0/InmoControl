package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.casouso.tipoparticipante.ModificarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ModificarTipoParticipanteCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.ModificarTipoParticipanteFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ModificarTipoParticipanteFachadaImpl implements ModificarTipoParticipanteFachada {

	private DAOFactory daoFactory;
	private ModificarTipoParticipanteCasoUso casoUso;

	public ModificarTipoParticipanteFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarTipoParticipanteCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(TipoParticipanteDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del tipo de participante no pueden ser nulos",
					"Validacion fallida en ModificarTipoParticipanteFachadaImpl.ejecutar() - Los datos del tipo de participante no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder().id(datos.getId())
					.nombre(datos.getNombre()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ModificarTipoParticipanteFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
