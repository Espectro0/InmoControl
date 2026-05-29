package com.inmocontrol.negocio.fachada.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.ciudad.impl.ConsultarCiudadPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarCiudadPorFiltrosFachadaImpl implements ConsultarCiudadPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarCiudadPorFiltrosCasoUso casoUso;

	public ConsultarCiudadPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarCiudadPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<CiudadEntidad> ejecutar(CiudadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la ciudad no pueden ser nulos",
					"Validacion fallida en ConsultarCiudadPorFiltrosFachadaImpl.ejecutar() - Los datos de la ciudad no pueden ser nulos");
		}

		try {
			CiudadDominio dominio = new CiudadDominio.Builder().nombre(datos.getNombre())
					.departamento(new DepartamentoDominio.Builder().id(datos.getDepartamento().getId())
							.pais(new PaisDominio.Builder().id(datos.getDepartamento().getPais().getId()).build())
							.build())
					.build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarCiudadPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
