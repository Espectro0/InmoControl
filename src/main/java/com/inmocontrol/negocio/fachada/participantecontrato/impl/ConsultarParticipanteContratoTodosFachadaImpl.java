package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoTodosCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.ConsultarParticipanteContratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.participantecontrato.ConsultarParticipanteContratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarParticipanteContratoTodosFachadaImpl implements ConsultarParticipanteContratoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarParticipanteContratoTodosCasoUso casoUso;

	public ConsultarParticipanteContratoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParticipanteContratoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ParticipanteContratoEntidad> ejecutar() {
		try {
			return casoUso.ejecutar();

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarParticipanteContratoTodosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
