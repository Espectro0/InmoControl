package com.inmocontrol.negocio.casouso.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarTipoDocumentoPorFiltrosCasoUsoImpl implements ConsultarTipoDocumentoPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoDocumentoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoDocumentoEntidad> ejecutar(TipoDocumentoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerTipoDocumentoDAO().consultarTodos();
		}
		return daoFactory.obtenerTipoDocumentoDAO()
				.consultarPorFiltro(new TipoDocumentoEntidad.Builder().nombre(datos.getNombre()).build());
	}
}