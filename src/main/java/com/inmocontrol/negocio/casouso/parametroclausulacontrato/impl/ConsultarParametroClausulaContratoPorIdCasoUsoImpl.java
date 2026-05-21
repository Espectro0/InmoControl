package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarParametroClausulaContratoPorIdCasoUsoImpl
		implements ConsultarParametroClausulaContratoPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarParametroClausulaContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ParametroClausulaContratoEntidad ejecutar(ParametroClausulaContratoDominio datos) {
		validarObligatoriedadIdParametroClausulaContrato(datos);
		return consultarParametroClausulaContrato(datos);
	}

	private void validarObligatoriedadIdParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El parametro clausula contrato a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del parametro clausula contrato es obligatorio.");
		}
	}

	private ParametroClausulaContratoEntidad consultarParametroClausulaContrato(
			ParametroClausulaContratoDominio datos) {
		ParametroClausulaContratoEntidad entidad = daoFactory.obtenerParametroClausulaContratoDAO()
				.consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new ValidacionExcepcion("No existe un parametro clausula contrato con el ID: " + datos.getId());
		}
		return entidad;
	}
}