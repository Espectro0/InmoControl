package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarPropiedadPorFiltrosCasoUsoImpl implements ConsultarPropiedadPorFiltrosCasoUso {

	private DAOFactory daoFactory;

	public ConsultarPropiedadPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<PropiedadEntidad> ejecutar(PropiedadDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			return daoFactory.obtenerPropiedadDAO().consultarTodos();
		}
		return daoFactory.obtenerPropiedadDAO().consultarPorFiltro(new PropiedadEntidad.Builder()
				.nombreInmueble(datos.getNombreInmueble()).direccion(datos.getDireccion()).build());
	}
}