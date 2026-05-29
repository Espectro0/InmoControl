package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

public class ConsultarClausulaContratoPorIdCasoUsoImpl implements ConsultarClausulaContratoPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarClausulaContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ClausulaContratoEntidad ejecutar(ClausulaContratoDominio datos) {
		validarObligatoriedadIdClausulaContrato(datos);
		return consultarClausulaContrato(datos);
	}

	private void validarObligatoriedadIdClausulaContrato(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("La clausula contrato a consultar no es valida.",
					"Validacion fallida en ConsultarClausulaContratoPorIdCasoUsoImpl.validarObligatoriedadIdClausulaContrato() - La clausula contrato a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID de la clausula contrato es obligatorio.",
					"Validacion fallida en ConsultarClausulaContratoPorIdCasoUsoImpl.validarObligatoriedadIdClausulaContrato() - El ID de la clausula contrato es obligatorio.");
		}
	}

	private ClausulaContratoEntidad consultarClausulaContrato(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad entidad = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new InmocontrolExcepcion("No existe una clausula contrato con el ID: " + datos.getId(),
					"Error en ConsultarClausulaContratoPorIdCasoUsoImpl.consultarClausulaContrato() - No existe una clausula contrato con el ID: "
							+ datos.getId());
		}
		return entidad;
	}
}
