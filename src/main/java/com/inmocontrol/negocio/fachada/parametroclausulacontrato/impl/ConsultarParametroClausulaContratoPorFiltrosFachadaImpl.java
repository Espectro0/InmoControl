package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.ConsultarParametroClausulaContratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.ConsultarParametroClausulaContratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.util.List;

public class ConsultarParametroClausulaContratoPorFiltrosFachadaImpl
		implements ConsultarParametroClausulaContratoPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarParametroClausulaContratoPorFiltrosCasoUso casoUso;

	public ConsultarParametroClausulaContratoPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParametroClausulaContratoPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ParametroClausulaContratoEntidad> ejecutar(ParametroClausulaContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro clausula contrato no pueden ser nulos",
					"Validacion fallida en ConsultarParametroClausulaContratoPorFiltrosFachadaImpl.ejecutar() - Los datos del parametro clausula contrato no pueden ser nulos");
		}

		try {
ParametroClausulaContratoDominio dominio = new ParametroClausulaContratoDominio.Builder()
				.parametro(datos.getParametro() != null
					? new com.inmocontrol.negocio.dominio.ParametroDominio.Builder()
						.id(datos.getParametro().getId()).build()
					: null)
				.clausulaPorContrato(datos.getClausulaPorContrato() != null
					? new com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio.Builder()
						.id(datos.getClausulaPorContrato().getId()).build()
					: null)
				.valor(datos.getValor()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarParametroClausulaContratoPorFiltrosFachadaImpl.ejecutar() - "
							+ excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
