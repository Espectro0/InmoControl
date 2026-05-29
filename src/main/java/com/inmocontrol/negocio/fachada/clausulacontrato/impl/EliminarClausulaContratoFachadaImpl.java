package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.clausulacontrato.EliminarClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.EliminarClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.EliminarClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class EliminarClausulaContratoFachadaImpl implements EliminarClausulaContratoFachada {

	private DAOFactory daoFactory;
	private EliminarClausulaContratoCasoUso casoUso;

	public EliminarClausulaContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarClausulaContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ClausulaContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la clausula contrato no pueden ser nulos",
					"Validacion fallida en EliminarClausulaContratoFachadaImpl.ejecutar() - Los datos de la clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new InmocontrolExcepcion("La clausula contrato con id " + datos.getId() + " no existe.",
						"Validacion fallida en EliminarClausulaContratoFachadaImpl.ejecutar() - La clausula contrato con id "
								+ datos.getId() + " no existe.");
			}
			ClausulaContratoDominio dominio = new ClausulaContratoDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en EliminarClausulaContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
