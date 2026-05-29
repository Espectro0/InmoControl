package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;

public class ConsultarAreaReferenciaPorIdCasoUsoImpl implements ConsultarAreaReferenciaPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarAreaReferenciaPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public AreaReferenciaEntidad ejecutar(AreaReferenciaDominio datos) {
		validarObligatoriedadIdAreaReferencia(datos);
		return consultarAreaReferencia(datos);
	}

	private void validarObligatoriedadIdAreaReferencia(AreaReferenciaDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("El area de referencia a consultar no es valida.",
					"Validacion fallida en ConsultarAreaReferenciaPorIdCasoUsoImpl.validarObligatoriedadIdAreaReferencia() - El area de referencia a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID del area de referencia es obligatorio.",
					"Validacion fallida en ConsultarAreaReferenciaPorIdCasoUsoImpl.validarObligatoriedadIdAreaReferencia() - El ID del area de referencia es obligatorio.");
		}
	}

	private AreaReferenciaEntidad consultarAreaReferencia(AreaReferenciaDominio datos) {
		AreaReferenciaEntidad entidad = daoFactory.obtenerAreaReferenciaDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(entidad)) {
			throw new InmocontrolExcepcion("No existe un area de referencia con el ID: " + datos.getId(),
					"Error en ConsultarAreaReferenciaPorIdCasoUsoImpl.consultarAreaReferencia() - No existe un area de referencia con el ID: "
							+ datos.getId());
		}
		return entidad;
	}
}
