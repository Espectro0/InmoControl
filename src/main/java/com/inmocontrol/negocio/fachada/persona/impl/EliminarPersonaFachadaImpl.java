package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.negocio.casouso.persona.EliminarPersonaCasoUso;
import com.inmocontrol.negocio.casouso.persona.impl.EliminarPersonaCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.fachada.persona.EliminarPersonaFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class EliminarPersonaFachadaImpl implements EliminarPersonaFachada {

	private DAOFactory daoFactory;
	private EliminarPersonaCasoUso casoUso;

	public EliminarPersonaFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarPersonaCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(PersonaDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la persona no pueden ser nulos",
					"Validacion fallida en EliminarPersonaFachadaImpl.ejecutar() - Los datos de la persona no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new InmocontrolExcepcion("La persona con id " + datos.getId() + " no existe.",
						"Validacion fallida en EliminarPersonaFachadaImpl.ejecutar() - La persona con id "
								+ datos.getId() + " no existe.");
			}
			PersonaDominio dominio = new PersonaDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en EliminarPersonaFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
