package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarClausulaPorContratoPorIdCasoUsoImpl implements ConsultarClausulaPorContratoPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarClausulaPorContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ClausulaPorContratoEntidad ejecutar(ClausulaPorContratoDominio datos) {
		validarObligatoriedadIdClausulaPorContrato(datos);
		return consultarClausulaPorContrato(datos);
	}

	private void validarObligatoriedadIdClausulaPorContrato(ClausulaPorContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La clausula por contrato a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la clausula por contrato es obligatorio.");
		}
	}

	private ClausulaPorContratoEntidad consultarClausulaPorContrato(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad entidad = daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new ValidacionExcepcion("No existe una clausula por contrato con el ID: " + datos.getId());
		}
		return entidad;
	}
}