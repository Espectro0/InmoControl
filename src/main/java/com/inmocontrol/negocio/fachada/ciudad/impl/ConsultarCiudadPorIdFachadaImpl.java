package com.inmocontrol.negocio.fachada.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorIdCasoUso;
import com.inmocontrol.negocio.casouso.ciudad.impl.ConsultarCiudadPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarCiudadPorIdFachadaImpl implements ConsultarCiudadPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarCiudadPorIdCasoUso casoUso;

	public ConsultarCiudadPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarCiudadPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public CiudadEntidad ejecutar(CiudadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la ciudad no pueden ser nulos",
					"Validacion fallida en ConsultarCiudadPorIdFachadaImpl.ejecutar() - Los datos de la ciudad no pueden ser nulos");
		}

		try {
			CiudadDominio dominio = new CiudadDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion",
					"Error en ConsultarCiudadPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
