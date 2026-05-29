package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipantePorIdCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ConsultarTipoParticipantePorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.ConsultarTipoParticipantePorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarTipoParticipantePorIdFachadaImpl implements ConsultarTipoParticipantePorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoParticipantePorIdCasoUso casoUso;

	public ConsultarTipoParticipantePorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoParticipantePorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public TipoParticipanteEntidad ejecutar(TipoParticipanteDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del tipo de participante no pueden ser nulos",
					"Validacion fallida en ConsultarTipoParticipantePorIdFachadaImpl.ejecutar() - Los datos del tipo de participante no pueden ser nulos");
		}

		try {
			TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarTipoParticipantePorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
