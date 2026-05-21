package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.RegistrarParametroCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarParametroCasoUsoImpl implements RegistrarParametroCasoUso {

	private DAOFactory daoFactory;

	public RegistrarParametroCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ParametroDominio datos) {
		validarObligatoriedadCampos(datos);
		registrarParametro(datos);
	}

	private void validarObligatoriedadCampos(ParametroDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El parametro a registrar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getPlaceholder()) || datos.getPlaceholder().isEmpty()) {
			throw new ValidacionExcepcion("El placeholder del parametro es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getDescripcion()) || datos.getDescripcion().isEmpty()) {
			throw new ValidacionExcepcion("La descripcion del parametro es obligatoria.");
		}
	}

	private void registrarParametro(ParametroDominio datos) {
		ParametroEntidad entidad = new ParametroEntidad.Builder().placeholder(datos.getPlaceholder())
				.descripcion(datos.getDescripcion()).build();
		daoFactory.obtenerParametroDAO().crear(entidad);
	}
}