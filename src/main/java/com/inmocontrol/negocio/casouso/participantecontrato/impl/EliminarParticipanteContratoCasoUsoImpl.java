package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.EliminarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarParticipanteContratoCasoUsoImpl implements EliminarParticipanteContratoCasoUso {

	private DAOFactory daoFactory;

	public EliminarParticipanteContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ParticipanteContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaParticipanteContrato(datos);
		eliminarParticipanteContrato(datos);
	}

	private void validarObligatoriedadId(ParticipanteContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El participante contrato a eliminar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del participante contrato es obligatorio.");
		}
	}

	private void validarExistenciaParticipanteContrato(ParticipanteContratoDominio datos) {
		ParticipanteContratoEntidad existente = daoFactory.obtenerParticipanteContratoDAO()
				.consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe un participante contrato con el ID: " + datos.getId());
		}
	}

	private void eliminarParticipanteContrato(ParticipanteContratoDominio datos) {
		daoFactory.obtenerParticipanteContratoDAO().eliminar(datos.getId());
	}
}