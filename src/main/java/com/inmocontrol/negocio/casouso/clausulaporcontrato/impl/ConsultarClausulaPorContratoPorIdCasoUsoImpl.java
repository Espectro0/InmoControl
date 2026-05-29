package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

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
			throw new InmocontrolExcepcion("La clausula por contrato a consultar no es valida.",
					"Validacion fallida en ConsultarClausulaPorContratoPorIdCasoUsoImpl.validarObligatoriedadIdClausulaPorContrato() - La clausula por contrato a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID de la clausula por contrato es obligatorio.",
					"Validacion fallida en ConsultarClausulaPorContratoPorIdCasoUsoImpl.validarObligatoriedadIdClausulaPorContrato() - El ID de la clausula por contrato es obligatorio.");
		}
	}

	private ClausulaPorContratoEntidad consultarClausulaPorContrato(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad entidad = daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new InmocontrolExcepcion("No existe una clausula por contrato con el ID: " + datos.getId(),
					"Error en ConsultarClausulaPorContratoPorIdCasoUsoImpl.consultarClausulaPorContrato() - No existe una clausula por contrato con el ID: "
							+ datos.getId());
		}
		return entidad;
	}
}
