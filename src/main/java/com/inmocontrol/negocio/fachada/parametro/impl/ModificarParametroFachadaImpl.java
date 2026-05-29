package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.negocio.casouso.parametro.ModificarParametroCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.ModificarParametroCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.ModificarParametroFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ModificarParametroFachadaImpl implements ModificarParametroFachada {

	private DAOFactory daoFactory;
	private ModificarParametroCasoUso casoUso;

	public ModificarParametroFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarParametroCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParametroDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro no pueden ser nulos",
					"Validacion fallida en ModificarParametroFachadaImpl.ejecutar() - Los datos del parametro no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParametroDominio dominio = new ParametroDominio.Builder().id(datos.getId())
					.placeholder(datos.getPlaceholder()).descripcion(datos.getDescripcion()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ModificarParametroFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
