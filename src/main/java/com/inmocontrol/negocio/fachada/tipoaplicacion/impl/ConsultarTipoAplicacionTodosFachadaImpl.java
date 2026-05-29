package com.inmocontrol.negocio.fachada.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionTodosCasoUso;
import com.inmocontrol.negocio.casouso.tipoaplicacion.impl.ConsultarTipoAplicacionTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarTipoAplicacionTodosFachadaImpl implements ConsultarTipoAplicacionTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoAplicacionTodosCasoUso casoUso;

	public ConsultarTipoAplicacionTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoAplicacionTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<TipoAplicacionEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarTipoAplicacionTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
