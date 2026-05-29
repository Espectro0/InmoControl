package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ModificarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.ModificarParametroClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.ModificarParametroClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ModificarParametroClausulaContratoFachadaImpl implements ModificarParametroClausulaContratoFachada {

	private DAOFactory daoFactory;
	private ModificarParametroClausulaContratoCasoUso casoUso;

	public ModificarParametroClausulaContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarParametroClausulaContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ParametroClausulaContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro clausula contrato no pueden ser nulos",
					"Validacion fallida en ModificarParametroClausulaContratoFachadaImpl.ejecutar() - Los datos del parametro clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParametroClausulaContratoDominio dominio = new ParametroClausulaContratoDominio.Builder().id(datos.getId())
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
					"Error en ModificarParametroClausulaContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
