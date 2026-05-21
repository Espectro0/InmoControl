package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.RegistrarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarTipoParticipanteCasoUsoImpl implements RegistrarTipoParticipanteCasoUso {

	private DAOFactory daoFactory;

	public RegistrarTipoParticipanteCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(TipoParticipanteDominio datos) {
		validarObligatoriedadCampos(datos);
		registrarTipoParticipante(datos);
	}

	private void validarObligatoriedadCampos(TipoParticipanteDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El tipo de participante a registrar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getNombre()) || datos.getNombre().isEmpty()) {
			throw new ValidacionExcepcion("El nombre del tipo de participante es obligatorio.");
		}
	}

	private void registrarTipoParticipante(TipoParticipanteDominio datos) {
		TipoParticipanteEntidad entidad = new TipoParticipanteEntidad.Builder().nombre(datos.getNombre()).build();
		daoFactory.obtenerTipoParticipanteDAO().crear(entidad);
	}
}