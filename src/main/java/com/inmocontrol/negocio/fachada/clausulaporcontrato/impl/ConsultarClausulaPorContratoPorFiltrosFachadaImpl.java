package com.inmocontrol.negocio.fachada.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.impl.ConsultarClausulaPorContratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.ConsultarClausulaPorContratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.util.List;

public class ConsultarClausulaPorContratoPorFiltrosFachadaImpl
		implements ConsultarClausulaPorContratoPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarClausulaPorContratoPorFiltrosCasoUso casoUso;

	public ConsultarClausulaPorContratoPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarClausulaPorContratoPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ClausulaPorContratoEntidad> ejecutar(ClausulaPorContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la clausula por contrato no pueden ser nulos",
					"Validacion fallida en ConsultarClausulaPorContratoPorFiltrosFachadaImpl.ejecutar() - Los datos de la clausula por contrato no pueden ser nulos");
		}

		try {
			ClausulaPorContratoDominio dominio = new ClausulaPorContratoDominio.Builder()
					.numeroClausula(datos.getNumeroClausula())
					.contrato(datos.getContrato() != null
							? new ContratoDominio.Builder().id(datos.getContrato().getId()).build()
							: null)
					.clausula(datos.getClausula() != null
							? new ClausulaContratoDominio.Builder().id(datos.getClausula().getId()).build()
							: null)
					.build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarClausulaPorContratoPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
