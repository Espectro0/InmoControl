package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipanteTodosCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ConsultarTipoParticipanteTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.tipoparticipante.ConsultarTipoParticipanteTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarTipoParticipanteTodosFachadaImpl implements ConsultarTipoParticipanteTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoParticipanteTodosCasoUso casoUso;

	public ConsultarTipoParticipanteTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoParticipanteTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<TipoParticipanteEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarTipoParticipanteTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
