package com.inmocontrol.negocio.fachada.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.departamento.impl.ConsultarDepartamentoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class ConsultarDepartamentoPorIdFachadaImpl implements ConsultarDepartamentoPorIdFachada {

	private DAOFactory daoFactory;
	private ConsultarDepartamentoPorIdCasoUso casoUso;

	public ConsultarDepartamentoPorIdFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarDepartamentoPorIdCasoUsoImpl(daoFactory);
	}

	@Override
	public DepartamentoEntidad ejecutar(DepartamentoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del departamento no pueden ser nulos",
					"Validacion fallida en ConsultarDepartamentoPorIdFachadaImpl.ejecutar() - Los datos del departamento no pueden ser nulos");
		}

		try {
			DepartamentoDominio dominio = new DepartamentoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarDepartamentoPorIdFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
