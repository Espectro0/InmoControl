package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaTodosCasoUso;
import com.inmocontrol.negocio.casouso.persona.impl.ConsultarPersonaTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.persona.ConsultarPersonaTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarPersonaTodosFachadaImpl implements ConsultarPersonaTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarPersonaTodosCasoUso casoUso;

	public ConsultarPersonaTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPersonaTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PersonaEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarPersonaTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
