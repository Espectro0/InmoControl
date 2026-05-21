package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarClausulaContratoPorFiltrosCasoUsoImpl implements ConsultarClausulaContratoPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarClausulaContratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<ClausulaContratoEntidad> ejecutar(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerClausulaContratoDAO().consultarTodos();
		}
		return daoFactory.obtenerClausulaContratoDAO()
				.consultarPorFiltro(new ClausulaContratoEntidad.Builder().titulo(datos.getTitulo()).build());
	}
}