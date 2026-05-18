package com.inmocontrol.negocio.fachada.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.departamento.impl.ConsultarDepartamentoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoPorIdFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

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
			throw new ValidacionExcepcion("Los datos del departamento no pueden ser nulos");
		}

		try {
			DepartamentoDominio dominio = new DepartamentoDominio.Builder().id(datos.getId()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}