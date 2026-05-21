package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ModificarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarTipoParticipanteCasoUsoImpl implements ModificarTipoParticipanteCasoUso {

	private DAOFactory daoFactory;

	public ModificarTipoParticipanteCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(TipoParticipanteDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaTipoParticipante(datos);
		modificarTipoParticipante(datos);
	}

	private void validarObligatoriedadId(TipoParticipanteDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El tipo de participante a modificar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del tipo de participante es obligatorio.");
		}
	}

	private void validarExistenciaTipoParticipante(TipoParticipanteDominio datos) {
		TipoParticipanteEntidad existente = daoFactory.obtenerTipoParticipanteDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe un tipo de participante con el ID: " + datos.getId());
		}
	}

	private void modificarTipoParticipante(TipoParticipanteDominio datos) {
		TipoParticipanteEntidad entidad = new TipoParticipanteEntidad.Builder().id(datos.getId())
				.nombre(datos.getNombre()).build();
		daoFactory.obtenerTipoParticipanteDAO().actualizar(entidad.getId(), entidad);
	}
}