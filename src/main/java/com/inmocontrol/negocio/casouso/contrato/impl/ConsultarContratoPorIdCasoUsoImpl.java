package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarContratoPorIdCasoUsoImpl implements ConsultarContratoPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarContratoPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ContratoEntidad ejecutar(ContratoDominio datos) {
		validarObligatoriedadIdContrato(datos);
		return consultarContrato(datos);
	}

	private void validarObligatoriedadIdContrato(ContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El contrato a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del contrato es obligatorio.");
		}
	}

	private ContratoEntidad consultarContrato(ContratoDominio datos) {
		ContratoEntidad entidad = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new ValidacionExcepcion("No existe un contrato con el ID: " + datos.getId());
		}
		return entidad;
	}
}