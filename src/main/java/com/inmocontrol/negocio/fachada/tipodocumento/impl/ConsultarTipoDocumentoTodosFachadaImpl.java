package com.inmocontrol.negocio.fachada.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoTodosCasoUso;
import com.inmocontrol.negocio.casouso.tipodocumento.impl.ConsultarTipoDocumentoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarTipoDocumentoTodosFachadaImpl implements ConsultarTipoDocumentoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoDocumentoTodosCasoUso casoUso;

	public ConsultarTipoDocumentoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoDocumentoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<TipoDocumentoEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarTipoDocumentoTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
