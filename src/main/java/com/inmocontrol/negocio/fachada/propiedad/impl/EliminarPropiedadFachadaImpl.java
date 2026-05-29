package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.negocio.casouso.propiedad.EliminarPropiedadCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.EliminarPropiedadCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.EliminarPropiedadFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class EliminarPropiedadFachadaImpl implements EliminarPropiedadFachada {

	private DAOFactory daoFactory;
	private EliminarPropiedadCasoUso casoUso;

	public EliminarPropiedadFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarPropiedadCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(PropiedadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la propiedad no pueden ser nulos",
					"Validacion fallida en EliminarPropiedadFachadaImpl.ejecutar() - Los datos de la propiedad no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new InmocontrolExcepcion("La propiedad con id " + datos.getId() + " no existe.",
						"Validacion fallida en EliminarPropiedadFachadaImpl.ejecutar() - La propiedad con id "
								+ datos.getId() + " no existe.");
			}
			PropiedadDominio dominio = new PropiedadDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en EliminarPropiedadFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
