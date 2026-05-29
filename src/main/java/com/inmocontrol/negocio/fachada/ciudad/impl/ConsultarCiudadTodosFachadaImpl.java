package com.inmocontrol.negocio.fachada.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadTodosCasoUso;
import com.inmocontrol.negocio.casouso.ciudad.impl.ConsultarCiudadTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarCiudadTodosFachadaImpl implements ConsultarCiudadTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarCiudadTodosCasoUso casoUso;

	public ConsultarCiudadTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarCiudadTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<CiudadEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarCiudadTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
