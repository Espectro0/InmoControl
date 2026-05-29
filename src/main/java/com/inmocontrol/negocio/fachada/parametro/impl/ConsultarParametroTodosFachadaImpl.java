package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroTodosCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.ConsultarParametroTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.parametro.ConsultarParametroTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarParametroTodosFachadaImpl implements ConsultarParametroTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarParametroTodosCasoUso casoUso;

	public ConsultarParametroTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParametroTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ParametroEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarParametroTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
