package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.ConsultarPropiedadPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.ConsultarPropiedadPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarPropiedadPorFiltrosFachadaImpl implements ConsultarPropiedadPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarPropiedadPorFiltrosCasoUso casoUso;

	public ConsultarPropiedadPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPropiedadPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PropiedadEntidad> ejecutar(PropiedadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("Los datos de la propiedad no pueden ser nulos");
		}

		try {
			PropiedadDominio dominio = new PropiedadDominio.Builder().nombreInmueble(datos.getNombreInmueble())
					.direccion(datos.getDireccion()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
