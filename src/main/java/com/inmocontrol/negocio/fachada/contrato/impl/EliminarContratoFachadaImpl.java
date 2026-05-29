package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.negocio.casouso.contrato.EliminarContratoCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.EliminarContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.contrato.EliminarContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class EliminarContratoFachadaImpl implements EliminarContratoFachada {

	private DAOFactory daoFactory;
	private EliminarContratoCasoUso casoUso;

	public EliminarContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del contrato no pueden ser nulos",
					"Validacion fallida en EliminarContratoFachadaImpl.ejecutar() - Los datos del contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new InmocontrolExcepcion("El contrato con id " + datos.getId() + " no existe.",
						"Validacion fallida en EliminarContratoFachadaImpl.ejecutar() - El contrato con id "
								+ datos.getId() + " no existe.");
			}
			ContratoDominio dominio = new ContratoDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en EliminarContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
