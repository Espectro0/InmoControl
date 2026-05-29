package com.inmocontrol.negocio.fachada.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.departamento.impl.ConsultarDepartamentoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.util.List;

public class ConsultarDepartamentoPorFiltrosFachadaImpl implements ConsultarDepartamentoPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarDepartamentoPorFiltrosCasoUso casoUso;

	public ConsultarDepartamentoPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarDepartamentoPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<DepartamentoEntidad> ejecutar(DepartamentoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del departamento no pueden ser nulos",
					"Validacion fallida en ConsultarDepartamentoPorFiltrosFachadaImpl.ejecutar() - Los datos del departamento no pueden ser nulos");
		}

		try {
			DepartamentoDominio dominio = new DepartamentoDominio.Builder().nombre(datos.getNombre())
					.pais(new PaisDominio.Builder().id(datos.getPais().getId()).build()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarDepartamentoPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
