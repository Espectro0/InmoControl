package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroPorIdCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.ConsultarParametroPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.ConsultarParametroPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarParametroPorIdFachadaImpl implements ConsultarParametroPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarParametroPorIdCasoUso casoUso;

	public ConsultarParametroPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParametroPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public ParametroEntidad ejecutar(ParametroDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del parametro no pueden ser nulos",
					"Validacion fallida en ConsultarParametroPorIdFachadaImpl.ejecutar() - Los datos del parametro no pueden ser nulos");
		}

		try {
			ParametroDominio dominio = new ParametroDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarParametroPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
