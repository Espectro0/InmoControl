package com.inmocontrol.negocio.casouso.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarTipoAplicacionPorFiltrosCasoUsoImpl implements ConsultarTipoAplicacionPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoAplicacionPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoAplicacionEntidad> ejecutar(TipoAplicacionDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerTipoAplicacionDAO().consultarTodos();
		}
		return daoFactory.obtenerTipoAplicacionDAO()
				.consultarPorFiltro(new TipoAplicacionEntidad.Builder().nombre(datos.getNombre()).build());
	}
}