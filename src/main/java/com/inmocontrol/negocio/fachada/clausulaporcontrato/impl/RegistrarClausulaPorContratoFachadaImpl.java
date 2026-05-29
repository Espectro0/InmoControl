package com.inmocontrol.negocio.fachada.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.RegistrarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.impl.RegistrarClausulaPorContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.RegistrarClausulaPorContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

public class RegistrarClausulaPorContratoFachadaImpl implements RegistrarClausulaPorContratoFachada {

	private DAOFactory daoFactory;
	private RegistrarClausulaPorContratoCasoUso casoUso;

	public RegistrarClausulaPorContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarClausulaPorContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ClausulaPorContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la clausula por contrato no pueden ser nulos",
					"Validacion fallida en RegistrarClausulaPorContratoFachadaImpl.ejecutar() - Los datos de la clausula por contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ClausulaPorContratoDominio dominio = new ClausulaPorContratoDominio.Builder()
					.numeroClausula(datos.getNumeroClausula())
					.contrato(new ContratoDominio.Builder().id(datos.getContrato().getId()).build())
					.clausula(new ClausulaContratoDominio.Builder().id(datos.getClausula().getId()).build())
					.build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarClausulaPorContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
