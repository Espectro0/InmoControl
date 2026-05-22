package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.clausulacontrato.EliminarClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.EliminarClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.EliminarClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
			throw new ValidacionExcepcion("Los datos de la clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			var existente = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
			if (existente == null) {
				throw new ValidacionExcepcion(
				    "La clausula contrato con id " + datos.getId() + " no existe.");
			}
			ClausulaContratoDominio dominio = new ClausulaContratoDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error eliminando la clausula contrato", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
