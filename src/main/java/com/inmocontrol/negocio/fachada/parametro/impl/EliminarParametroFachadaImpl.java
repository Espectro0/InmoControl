package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.negocio.casouso.parametro.EliminarParametroCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.EliminarParametroCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.EliminarParametroFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarParametroFachadaImpl implements EliminarParametroFachada {

	private DAOFactory daoFactory;
	private EliminarParametroCasoUso casoUso;

	public EliminarParametroFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarParametroCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParametroDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("Los datos del parametro no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new ValidacionExcepcion(
				    "El parametro con id " + datos.getId() + " no existe.");
			}
			ParametroDominio dominio = new ParametroDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error eliminando el parametro", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
