package com.inmocontrol.datos.dao.sql.factoria;

import java.sql.Connection;

import com.inmocontrol.datos.dao.AreaReferenciaDAO;
import com.inmocontrol.datos.dao.CiudadDAO;
import com.inmocontrol.datos.dao.ClausulaContratoDAO;
import com.inmocontrol.datos.dao.ClausulaPorContratoDAO;
import com.inmocontrol.datos.dao.ContratoDAO;
import com.inmocontrol.datos.dao.DepartamentoDAO;
import com.inmocontrol.datos.dao.EstratoDAO;
import com.inmocontrol.datos.dao.PaisDAO;
import com.inmocontrol.datos.dao.ParametroClausulaContratoDAO;
import com.inmocontrol.datos.dao.ParametroDAO;
import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.dao.PersonaDAO;
import com.inmocontrol.datos.dao.PropiedadDAO;
import com.inmocontrol.datos.dao.TipoAplicacionDAO;
import com.inmocontrol.datos.dao.TipoDocumentoDAO;
import com.inmocontrol.datos.dao.TipoParticipanteDAO;
import com.inmocontrol.datos.dao.TipoPropiedadDAO;
import com.inmocontrol.datos.dao.sql.factoria.postgresql.PostgreSQLDAOFactory;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public abstract class DAOFactory {

	protected Connection conexion;
	private static TipoFactoriaEnum FACTORIA_ACTUAL = TipoFactoriaEnum.POSTGRESQL;

	public static DAOFactory getFactory() {
		switch (FACTORIA_ACTUAL) {
		case POSTGRESQL:
			return new PostgreSQLDAOFactory();
		default:
			throw new InmocontrolExcepcion("Tipo de factoria actual no soportada: " + FACTORIA_ACTUAL);
		}
	}

	protected abstract void abrirConexion();

	public abstract void cerrarConexion();

	public abstract void iniciarTransaccion();

	public abstract void confirmarTransaccion();

	public abstract void cancelarTransaccion();

	public abstract PaisDAO getPaisDAO();

	public abstract EstratoDAO getEstratoDAO();

	public abstract TipoDocumentoDAO getTipoDocumentoDAO();

	public abstract TipoPropiedadDAO getTipoPropiedadDAO();

	public abstract TipoParticipanteDAO getTipoParticipanteDAO();

	public abstract TipoAplicacionDAO getTipoAplicacionDAO();

	public abstract ParametroDAO getParametroDAO();

	public abstract AreaReferenciaDAO getAreaReferenciaDAO();

	public abstract DepartamentoDAO getDepartamentoDAO();

	public abstract CiudadDAO getCiudadDAO();

	public abstract ClausulaContratoDAO getClausulaContratoDAO();

	public abstract PersonaDAO getPersonaDAO();

	public abstract ParticipanteContratoDAO getParticipanteContratoDAO();

	public abstract PropiedadDAO getPropiedadDAO();

	public abstract ParametroClausulaContratoDAO getParametroClausulaContratoDAO();

	public abstract ContratoDAO getContratoDAO();

	public abstract ClausulaPorContratoDAO getClausulaPorContratoDAO();
}