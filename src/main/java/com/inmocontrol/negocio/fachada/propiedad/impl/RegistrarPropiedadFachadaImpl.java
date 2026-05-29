package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.negocio.casouso.propiedad.RegistrarPropiedadCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.RegistrarPropiedadCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.RegistrarPropiedadFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class RegistrarPropiedadFachadaImpl implements RegistrarPropiedadFachada {

	private DAOFactory daoFactory;
	private RegistrarPropiedadCasoUso casoUso;

	public RegistrarPropiedadFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarPropiedadCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(PropiedadDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la propiedad no pueden ser nulos",
					"Validacion fallida en RegistrarPropiedadFachadaImpl.ejecutar() - Los datos de la propiedad no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			PropiedadDominio dominio = new PropiedadDominio.Builder()
					.tipoPropiedad(new TipoPropiedadDominio.Builder().id(datos.getTipoPropiedad().getId()).build())
					.estrato(new EstratoDominio.Builder().id(datos.getEstrato().getId()).build())
					.nombreInmueble(datos.getNombreInmueble()).descripcionInmueble(datos.getDescripcionInmueble())
					.areaMetros(datos.getAreaMetros()).direccion(datos.getDireccion())
					.ciudad(new CiudadDominio.Builder().id(datos.getCiudad().getId()).build())
					.build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarPropiedadFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
