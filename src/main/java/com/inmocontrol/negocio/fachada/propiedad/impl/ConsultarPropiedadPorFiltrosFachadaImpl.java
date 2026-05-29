package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.ConsultarPropiedadPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.ConsultarPropiedadPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

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
			throw new InmocontrolExcepcion("Los datos de la propiedad no pueden ser nulos",
					"Validacion fallida en ConsultarPropiedadPorFiltrosFachadaImpl.ejecutar() - Los datos de la propiedad no pueden ser nulos");
		}

		try {
			PropiedadDominio dominio = new PropiedadDominio.Builder().nombreInmueble(datos.getNombreInmueble())
					.direccion(datos.getDireccion())
					.tipoPropiedad(datos.getTipoPropiedad() != null
							? new TipoPropiedadDominio.Builder().id(datos.getTipoPropiedad().getId()).build()
							: null)
					.estrato(datos.getEstrato() != null
							? new EstratoDominio.Builder().id(datos.getEstrato().getId()).build()
							: null)
					.ciudad(datos.getCiudad() != null
							? new CiudadDominio.Builder().id(datos.getCiudad().getId()).build()
							: null)
					.areaMetros(datos.getAreaMetros()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarPropiedadPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
