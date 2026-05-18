package com.inmocontrol.negocio.casouso.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorIdCasoUso;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarCiudadPorIdCasoUsoImpl implements ConsultarCiudadPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarCiudadPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public CiudadEntidad ejecutar(CiudadDominio datos) {
		validarObligatoriedadIdCiudad(datos);
		return consultarCiudad(datos);
	}

	private void validarObligatoriedadIdCiudad(CiudadDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La ciudad a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la ciudad es obligatorio.");
		}
	}

	private CiudadEntidad consultarCiudad(CiudadDominio datos) {
		CiudadEntidad ciudadEntidad = daoFactory.obtenerCiudadDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(ciudadEntidad)) {
			throw new ValidacionExcepcion("No existe una ciudad con el ID: " + datos.getId());
		}
		return ciudadEntidad;
	}
}