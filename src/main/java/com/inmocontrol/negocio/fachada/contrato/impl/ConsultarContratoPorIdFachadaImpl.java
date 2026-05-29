package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.ConsultarContratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.contrato.ConsultarContratoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarContratoPorIdFachadaImpl implements ConsultarContratoPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarContratoPorIdCasoUso casoUso;

	public ConsultarContratoPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarContratoPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public ContratoEntidad ejecutar(ContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del contrato no pueden ser nulos",
					"Validacion fallida en ConsultarContratoPorIdFachadaImpl.ejecutar() - Los datos del contrato no pueden ser nulos");
		}

		try {
			ContratoDominio dominio = new ContratoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarContratoPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
