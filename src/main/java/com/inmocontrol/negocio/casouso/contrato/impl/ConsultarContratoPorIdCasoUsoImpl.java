package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

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
			throw new InmocontrolExcepcion("El contrato a consultar no es valido.",
					"Validacion fallida en ConsultarContratoPorIdCasoUsoImpl.validarObligatoriedadIdContrato() - El contrato a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID del contrato es obligatorio.",
					"Validacion fallida en ConsultarContratoPorIdCasoUsoImpl.validarObligatoriedadIdContrato() - El ID del contrato es obligatorio.");
		}
	}

	private ContratoEntidad consultarContrato(ContratoDominio datos) {
		ContratoEntidad entidad = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new InmocontrolExcepcion("No existe un contrato con el ID: " + datos.getId(),
					"Error en ConsultarContratoPorIdCasoUsoImpl.consultarContrato() - No existe un contrato con el ID: "
							+ datos.getId());
		}
		return entidad;
	}
}
