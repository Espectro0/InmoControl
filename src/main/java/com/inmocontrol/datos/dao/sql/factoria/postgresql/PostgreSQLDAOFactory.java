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

	@Override
	protected void abrirConexion() {
		try {
			String host = UtilEnv.get("BD_HOST");
			String port = UtilEnv.get("BD_PORT");
			String dbName = UtilEnv.get("BD_NAME");
			String user = UtilEnv.get("BD_USR");
			String password = UtilEnv.get("BD_PWD");

			String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
			conexion = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo establecer la conexion a la base de datos", e);
		}
	}

	@Override
	public void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				throw new InmocontrolExcepcion("No se pudo cerrar la conexion", e);
			}
		}
	}

	@Override
	public void iniciarTransaccion() {
		if (conexion != null) {
			try {
				conexion.setAutoCommit(false);
			} catch (SQLException e) {
				throw new InmocontrolExcepcion("No se pudo iniciar la transaccion", e);
			}
		}
	}

	@Override
	public void confirmarTransaccion() {
		if (conexion != null) {
			try {
				conexion.commit();
			} catch (SQLException e) {
				throw new InmocontrolExcepcion("No se pudo confirmar la transaccion", e);
			}
		}
	}

	@Override
	public void cancelarTransaccion() {
		if (conexion != null) {
			try {
				conexion.rollback();
			} catch (SQLException e) {
				throw new InmocontrolExcepcion("No se pudo cancelar la transaccion", e);
			}
		}
	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisPostgresqlDAO(conexion);
	}

	@Override
	public EstratoDAO getEstratoDAO() {
		return new EstratoPostgresqlDAO(conexion);
	}

	@Override
	public TipoDocumentoDAO getTipoDocumentoDAO() {
		return new TipoDocumentoPostgresqlDAO(conexion);
	}

	@Override
	public TipoPropiedadDAO getTipoPropiedadDAO() {
		return new TipoPropiedadPostgresqlDAO(conexion);
	}

	@Override
	public TipoParticipanteDAO getTipoParticipanteDAO() {
		return new TipoParticipantePostgresqlDAO(conexion);
	}

	@Override
	public TipoAplicacionDAO getTipoAplicacionDAO() {
		return new TipoAplicacionPostgresqlDAO(conexion);
	}

	@Override
	public ParametroDAO getParametroDAO() {
		return new ParametroPostgresqlDAO(conexion);
	}

	@Override
	public AreaReferenciaDAO getAreaReferenciaDAO() {
		return new AreaReferenciaPostgresqlDAO(conexion);
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoPostgresqlDAO(conexion);
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadPostgresqlDAO(conexion);
	}

	@Override
	public ClausulaContratoDAO getClausulaContratoDAO() {
		return new ClausulaContratoPostgresqlDAO(conexion);
	}

	@Override
	public PersonaDAO getPersonaDAO() {
		return new PersonaPostgresqlDAO(conexion);
	}

	@Override
	public ParticipanteContratoDAO getParticipanteContratoDAO() {
		return new ParticipanteContratoPostgresqlDAO(conexion);
	}

	@Override
	public PropiedadDAO getPropiedadDAO() {
		return new PropiedadPostgresqlDAO(conexion);
	}

	@Override
	public ParametroClausulaContratoDAO getParametroClausulaContratoDAO() {
		return new ParametroClausulaContratoPostgresqlDAO(conexion);
	}

	@Override
	public ContratoDAO getContratoDAO() {
		return new ContratoPostgresqlDAO(conexion);
	}

	@Override
	public ClausulaPorContratoDAO getClausulaPorContratoDAO() {
		return new ClausulaPorContratoPostgresqlDAO(conexion);
	}
}