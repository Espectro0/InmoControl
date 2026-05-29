package com.inmocontrol.negocio.fachada.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisTodosCasoUso;
import com.inmocontrol.negocio.casouso.pais.impl.ConsultarPaisTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarPaisTodosFachadaImpl implements ConsultarPaisTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarPaisTodosCasoUso casoUso;

	public ConsultarPaisTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPaisTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PaisEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarPaisTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
