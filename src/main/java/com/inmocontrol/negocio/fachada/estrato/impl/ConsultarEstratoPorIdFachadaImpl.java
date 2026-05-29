package com.inmocontrol.negocio.fachada.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.estrato.impl.ConsultarEstratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarEstratoPorIdFachadaImpl implements ConsultarEstratoPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarEstratoPorIdCasoUso casoUso;

	public ConsultarEstratoPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarEstratoPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public EstratoEntidad ejecutar(EstratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del estrato no pueden ser nulos",
					"Validacion fallida en ConsultarEstratoPorIdFachadaImpl.ejecutar() - Los datos del estrato no pueden ser nulos");
		}

		try {
			EstratoDominio dominio = new EstratoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarEstratoPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
