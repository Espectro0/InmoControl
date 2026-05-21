package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ConsultarTipoParticipanteTodosCasoUsoImpl;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipanteTodosCasoUso;
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
	public List<TipoParticipanteEntidad> ejecutar(Void datos) {
		try {
			return casoUso.ejecutar(datos);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}