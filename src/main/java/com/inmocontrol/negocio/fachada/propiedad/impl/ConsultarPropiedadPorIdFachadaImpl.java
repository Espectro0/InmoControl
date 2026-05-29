package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorIdCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.ConsultarPropiedadPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.ConsultarPropiedadPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;


public class ConsultarPropiedadPorIdFachadaImpl implements ConsultarPropiedadPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarPropiedadPorIdCasoUso casoUso;

	public ConsultarPropiedadPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPropiedadPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public PropiedadEntidad ejecutar(PropiedadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion(
			    "Los datos de la propiedad no pueden ser nulos",
			    "Validacion fallida en ConsultarPropiedadPorIdFachadaImpl.ejecutar() - datos nulos");
		}

		try {
			PropiedadDominio dominio = new PropiedadDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion(
			    "Ocurrio un error obteniendo la informacion",
			    "Error en ConsultarPropiedadPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(),
			    excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}

