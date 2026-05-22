package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ModificarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.ModificarParametroClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.ModificarParametroClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
			throw new ValidacionExcepcion("Los datos del parametro clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ParametroClausulaContratoDominio dominio = new ParametroClausulaContratoDominio.Builder().id(datos.getId())
					.parametro(
							datos.getParametro() != null
									? new com.inmocontrol.negocio.dominio.ParametroDominio.Builder()
											.id(datos.getParametro().getId()).build()
									: null)
					.clausulaPorContrato(datos.getClausulaPorContrato() != null
							? new com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio.Builder()
									.id(datos.getClausulaPorContrato().getId()).build()
							: null)
					.valor(datos.getValor()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("Ocurrio un error modificando el parametro clausula contrato", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
