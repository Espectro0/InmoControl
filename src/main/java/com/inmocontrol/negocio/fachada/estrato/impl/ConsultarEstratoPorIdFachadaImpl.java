package com.inmocontrol.negocio.fachada.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.estrato.impl.ConsultarEstratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorIdFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

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
			throw new ValidacionExcepcion("Los datos del estrato no pueden ser nulos");
		}

		try {
			EstratoDominio dominio = new EstratoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}