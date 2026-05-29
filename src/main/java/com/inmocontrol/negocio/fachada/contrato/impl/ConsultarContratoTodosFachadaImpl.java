package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoTodosCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.ConsultarContratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.contrato.ConsultarContratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarContratoTodosFachadaImpl implements ConsultarContratoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarContratoTodosCasoUso casoUso;

	public ConsultarContratoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarContratoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ContratoEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarContratoTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
