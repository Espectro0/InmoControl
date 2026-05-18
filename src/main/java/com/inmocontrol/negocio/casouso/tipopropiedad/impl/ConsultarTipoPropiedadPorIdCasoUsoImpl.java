package com.inmocontrol.negocio.casouso.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadPorIdCasoUso;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoPropiedadPorIdCasoUsoImpl implements ConsultarTipoPropiedadPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoPropiedadPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public TipoPropiedadEntidad ejecutar(TipoPropiedadDominio datos) {
		validarObligatoriedadIdTipoPropiedad(datos);
		return consultarTipoPropiedad(datos);
	}

	private void validarObligatoriedadIdTipoPropiedad(TipoPropiedadDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El tipo de propiedad a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del tipo de propiedad es obligatorio.");
		}
	}

	private TipoPropiedadEntidad consultarTipoPropiedad(TipoPropiedadDominio datos) {
		TipoPropiedadEntidad tipoPropiedadEntidad = daoFactory.obtenerTipoPropiedadDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(tipoPropiedadEntidad)) {
			throw new ValidacionExcepcion("No existe un tipo de propiedad con el ID: " + datos.getId());
		}
		return tipoPropiedadEntidad;
	}
}