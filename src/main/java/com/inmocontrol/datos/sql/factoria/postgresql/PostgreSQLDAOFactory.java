package com.inmocontrol.datos.sql.factoria.postgresql;

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
import com.inmocontrol.datos.sql.factoria.DAOFactory;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDAOFactory extends DAOFactory {

    public PostgreSQLDAOFactory() {
        abrirConexion();
    }

    @Override
    protected void abrirConexion() {
        try {
            Dotenv env = Dotenv.load();

            String servidor = env.get("BD_HOST");
            String puerto = env.get("BD_PORT");
            String baseDatos = env.get("BD_NAME");
            String usuario = env.get("BD_USR");
            String contrasena = env.get("BD_PWD");

            String urlConexion = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + baseDatos;

            conexion = DriverManager.getConnection(urlConexion, usuario, contrasena);
        } catch (SQLException e) {
            conexion = null;
            throw new TransaccionExcepcion(
                    "Error al abrir la conexión a la base de datos PostgreSQL", e);
        }
    }

    @Override
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Error al cerrar la conexión de la base de datos PostgreSQL", e);
        } finally {
            conexion = null;
        }
    }

    @Override
    public void iniciarTransaccion() {
        try {
            if (esConexionValida()) {
                conexion.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Error al iniciar la transacción de la base de datos PostgreSQL", e);
        }
    }

    @Override
    public void confirmarTransaccion() {
        try {
            if (esConexionValida()) {
                conexion.commit();
                conexion.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Error al confirmar la transacción de la base de datos PostgreSQL", e);
        }
    }

    @Override
    public void cancelarTransaccion() {
        try {
            if (esConexionValida()) {
                conexion.rollback();
                conexion.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Error al cancelar la transacción de la base de datos PostgreSQL", e);
        }
    }

    private boolean esConexionValida() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException _) {
            return false;
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
