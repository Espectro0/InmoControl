package com.inmocontrol.negocio.casouso.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionPorIdCasoUso;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoAplicacionPorIdCasoUsoImpl implements ConsultarTipoAplicacionPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoAplicacionPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public TipoAplicacionEntidad ejecutar(TipoAplicacionDominio datos) {
		validarObligatoriedadIdTipoAplicacion(datos);
		return consultarTipoAplicacion(datos);
	}

	private void validarObligatoriedadIdTipoAplicacion(TipoAplicacionDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El tipo de aplicacion a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del tipo de aplicacion es obligatorio.");
		}
	}

	private TipoAplicacionEntidad consultarTipoAplicacion(TipoAplicacionDominio datos) {
		TipoAplicacionEntidad tipoAplicacionEntidad = daoFactory.obtenerTipoAplicacionDAO()
				.consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(tipoAplicacionEntidad)) {
			throw new ValidacionExcepcion("No existe un tipo de aplicacion con el ID: " + datos.getId());
		}
		return tipoAplicacionEntidad;
	}
}