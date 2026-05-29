package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoTodosCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.ConsultarClausulaContratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.clausulacontrato.ConsultarClausulaContratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarClausulaContratoTodosFachadaImpl implements ConsultarClausulaContratoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarClausulaContratoTodosCasoUso casoUso;

	public ConsultarClausulaContratoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarClausulaContratoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ClausulaContratoEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarClausulaContratoTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
