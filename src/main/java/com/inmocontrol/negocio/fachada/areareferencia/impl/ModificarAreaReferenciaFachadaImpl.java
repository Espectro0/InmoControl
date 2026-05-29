package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.negocio.casouso.areareferencia.ModificarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.ModificarAreaReferenciaCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.fachada.areareferencia.ModificarAreaReferenciaFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ModificarAreaReferenciaFachadaImpl implements ModificarAreaReferenciaFachada {

	private DAOFactory daoFactory;
	private ModificarAreaReferenciaCasoUso casoUso;

	public ModificarAreaReferenciaFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarAreaReferenciaCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(AreaReferenciaDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del area de referencia no pueden ser nulos",
					"Validacion fallida en ModificarAreaReferenciaFachadaImpl.ejecutar() - Los datos del area de referencia no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			AreaReferenciaDominio dominio = new AreaReferenciaDominio.Builder().id(datos.getId())
					.nombre(datos.getNombre()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ModificarAreaReferenciaFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
