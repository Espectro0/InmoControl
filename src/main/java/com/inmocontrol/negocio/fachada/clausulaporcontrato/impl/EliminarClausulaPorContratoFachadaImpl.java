package com.inmocontrol.negocio.fachada.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.EliminarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.impl.EliminarClausulaPorContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.EliminarClausulaPorContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarClausulaPorContratoFachadaImpl implements EliminarClausulaPorContratoFachada {

	private DAOFactory daoFactory;
	private EliminarClausulaPorContratoCasoUso casoUso;

	public EliminarClausulaPorContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarClausulaPorContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ClausulaPorContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("Los datos de la clausula por contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new ValidacionExcepcion(
				    "La clausula por contrato con id " + datos.getId() + " no existe.");
			}
			ClausulaPorContratoDominio dominio = new ClausulaPorContratoDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error eliminando la clausula por contrato", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
