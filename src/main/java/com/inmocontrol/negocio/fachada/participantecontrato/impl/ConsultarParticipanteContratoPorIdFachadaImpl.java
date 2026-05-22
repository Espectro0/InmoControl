package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.ConsultarParticipanteContratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.ConsultarParticipanteContratoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarParticipanteContratoPorIdFachadaImpl implements ConsultarParticipanteContratoPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarParticipanteContratoPorIdCasoUso casoUso;

	public ConsultarParticipanteContratoPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParticipanteContratoPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public ParticipanteContratoEntidad ejecutar(ParticipanteContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("Los datos del participante contrato no pueden ser nulos");
		}

		try {
			ParticipanteContratoDominio dominio = new ParticipanteContratoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
