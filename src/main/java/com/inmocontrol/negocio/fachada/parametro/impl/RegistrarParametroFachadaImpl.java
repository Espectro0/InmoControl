package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.negocio.casouso.parametro.RegistrarParametroCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.RegistrarParametroCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.RegistrarParametroFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class RegistrarParametroFachadaImpl implements RegistrarParametroFachada {

	private DAOFactory daoFactory;
	private RegistrarParametroCasoUso casoUso;

	public RegistrarParametroFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarParametroCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParametroDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro no pueden ser nulos",
					"Validacion fallida en RegistrarParametroFachadaImpl.ejecutar() - Los datos del parametro no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParametroDominio dominio = new ParametroDominio.Builder().placeholder(datos.getPlaceholder())
					.descripcion(datos.getDescripcion()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarParametroFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
