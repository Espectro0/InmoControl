package com.inmocontrol.datos.dao.sql.factoria.postgresql;

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
import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.datos.dao.sql.postgresql.AreaReferenciaPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.CiudadPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ClausulaContratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ClausulaPorContratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ContratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.DepartamentoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.EstratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.PaisPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ParametroClausulaContratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ParametroPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.ParticipanteContratoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.PersonaPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.PropiedadPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.TipoAplicacionPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.TipoDocumentoPostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.TipoParticipantePostgresqlDAO;
import com.inmocontrol.datos.dao.sql.postgresql.TipoPropiedadPostgresqlDAO;
import com.inmocontrol.transversal.UtilEnv;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDAOFactory extends DAOFactory {

	public PostgreSQLDAOFactory() {
		abrirConexion();
	}

	@Override
	protected void abrirConexion() {
		if (conexion != null) {
			return;
		}
		try {
			String host = UtilEnv.get("BD_HOST");
			String port = UtilEnv.get("BD_PORT");
			String dbName = UtilEnv.get("BD_NAME");
			String user = UtilEnv.get("BD_USR");
			String password = UtilEnv.get("BD_PWD");

			String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
			conexion = DriverManager.getConnection(url, user, password);
		} catch (SQLException excepcion) {
			var mensajeUsuario = "No se pudo establecer la conexion a la base de datos. Verifique que el servidor esta disponible.";
			var mensajeTecnico = "Error de conexion a PostgreSQL: " + excepcion.getMessage();
			throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException excepcion) {
				var mensajeUsuario = "Error al cerrar la conexion con la base de datos.";
				var mensajeTecnico = "Error al cerrar conexion: " + excepcion.getMessage();
				throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico, excepcion);
			}
		}
	}

	@Override
	public void iniciarTransaccion() {
		abrirConexion();
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException excepcion) {
			var mensajeUsuario = "No se pudo iniciar la transaccion en la base de datos.";
			var mensajeTecnico = "Error al iniciar transaccion: " + excepcion.getMessage();
			throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public void confirmarTransaccion() {
		if (conexion != null) {
			try {
				conexion.commit();
			} catch (SQLException excepcion) {
				var mensajeUsuario = "No se pudo confirmar la transaccion en la base de datos.";
				var mensajeTecnico = "Error al confirmar transaccion: " + excepcion.getMessage();
				throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico, excepcion);
			}
		}
	}

	@Override
	public void cancelarTransaccion() {
		if (conexion != null) {
			try {
				conexion.rollback();
			} catch (SQLException excepcion) {
				var mensajeUsuario = "No se pudo cancelar la transaccion en la base de datos.";
				var mensajeTecnico = "Error al cancelar transaccion: " + excepcion.getMessage();
				throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico, excepcion);
			}
		}
	}

	@Override
	public PaisDAO obtenerPaisDAO() {
		return new PaisPostgresqlDAO(conexion);
	}

	@Override
	public EstratoDAO obtenerEstratoDAO() {
		return new EstratoPostgresqlDAO(conexion);
	}

	@Override
	public TipoDocumentoDAO obtenerTipoDocumentoDAO() {
		return new TipoDocumentoPostgresqlDAO(conexion);
	}

	@Override
	public TipoPropiedadDAO obtenerTipoPropiedadDAO() {
		return new TipoPropiedadPostgresqlDAO(conexion);
	}

	@Override
	public TipoParticipanteDAO obtenerTipoParticipanteDAO() {
		return new TipoParticipantePostgresqlDAO(conexion);
	}

	@Override
	public TipoAplicacionDAO obtenerTipoAplicacionDAO() {
		return new TipoAplicacionPostgresqlDAO(conexion);
	}

	@Override
	public ParametroDAO obtenerParametroDAO() {
		return new ParametroPostgresqlDAO(conexion);
	}

	@Override
	public AreaReferenciaDAO obtenerAreaReferenciaDAO() {
		return new AreaReferenciaPostgresqlDAO(conexion);
	}

	@Override
	public DepartamentoDAO obtenerDepartamentoDAO() {
		return new DepartamentoPostgresqlDAO(conexion);
	}

	@Override
	public CiudadDAO obtenerCiudadDAO() {
		return new CiudadPostgresqlDAO(conexion);
	}

	@Override
	public ClausulaContratoDAO obtenerClausulaContratoDAO() {
		return new ClausulaContratoPostgresqlDAO(conexion);
	}

	@Override
	public PersonaDAO obtenerPersonaDAO() {
		return new PersonaPostgresqlDAO(conexion);
	}

	@Override
	public ParticipanteContratoDAO obtenerParticipanteContratoDAO() {
		return new ParticipanteContratoPostgresqlDAO(conexion);
	}

	@Override
	public PropiedadDAO obtenerPropiedadDAO() {
		return new PropiedadPostgresqlDAO(conexion);
	}

	@Override
	public ParametroClausulaContratoDAO obtenerParametroClausulaContratoDAO() {
		return new ParametroClausulaContratoPostgresqlDAO(conexion);
	}

	@Override
	public ContratoDAO obtenerContratoDAO() {
		return new ContratoPostgresqlDAO(conexion);
	}

	@Override
	public ClausulaPorContratoDAO obtenerClausulaPorContratoDAO() {
		return new ClausulaPorContratoPostgresqlDAO(conexion);
	}
}
