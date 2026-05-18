package com.inmocontrol.negocio.casouso.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarTipoPropiedadPorFiltrosCasoUsoImpl implements ConsultarTipoPropiedadPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoPropiedadPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoPropiedadEntidad> ejecutar(TipoPropiedadDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerTipoPropiedadDAO().consultarTodos();
		}
		return daoFactory.obtenerTipoPropiedadDAO()
				.consultarPorFiltro(new TipoPropiedadEntidad.Builder().nombre(datos.getNombre()).build());
	}
}