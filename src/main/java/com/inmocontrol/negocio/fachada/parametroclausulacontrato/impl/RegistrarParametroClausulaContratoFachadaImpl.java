package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.RegistrarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.RegistrarParametroClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.RegistrarParametroClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class RegistrarParametroClausulaContratoFachadaImpl implements RegistrarParametroClausulaContratoFachada {

	private DAOFactory daoFactory;
	private RegistrarParametroClausulaContratoCasoUso casoUso;

	public RegistrarParametroClausulaContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarParametroClausulaContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParametroClausulaContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro clausula contrato no pueden ser nulos",
					"Validacion fallida en RegistrarParametroClausulaContratoFachadaImpl.ejecutar() - Los datos del parametro clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParametroClausulaContratoDominio dominio = new ParametroClausulaContratoDominio.Builder()
					.parametro(new com.inmocontrol.negocio.dominio.ParametroDominio.Builder()
							.id(datos.getParametro().getId()).build())
					.clausulaPorContrato(new com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio.Builder()
							.id(datos.getClausulaPorContrato().getId()).build())
					.valor(datos.getValor()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarParametroClausulaContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
