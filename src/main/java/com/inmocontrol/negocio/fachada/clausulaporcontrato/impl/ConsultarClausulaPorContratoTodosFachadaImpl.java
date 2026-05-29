package com.inmocontrol.negocio.fachada.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoTodosCasoUso;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.impl.ConsultarClausulaPorContratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.ConsultarClausulaPorContratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarClausulaPorContratoTodosFachadaImpl implements ConsultarClausulaPorContratoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarClausulaPorContratoTodosCasoUso casoUso;

	public ConsultarClausulaPorContratoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarClausulaPorContratoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ClausulaPorContratoEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarClausulaPorContratoTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
