package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarParametroPorFiltrosCasoUsoImpl implements ConsultarParametroPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarParametroPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<ParametroEntidad> ejecutar(ParametroDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerParametroDAO().consultarTodos();
		}
		return daoFactory.obtenerParametroDAO().consultarPorFiltro(new ParametroEntidad.Builder()
				.placeholder(datos.getPlaceholder()).descripcion(datos.getDescripcion()).build());
	}
}