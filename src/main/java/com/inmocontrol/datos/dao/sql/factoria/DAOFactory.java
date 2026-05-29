package com.inmocontrol.datos.dao.sql.factoria;

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
import java.sql.Connection;

public abstract class DAOFactory {

  protected Connection conexion;
  private static TipoFactoriaEnum FACTORIA_ACTUAL = TipoFactoriaEnum.POSTGRESQL;

  public static DAOFactory getFactory() {
    switch (FACTORIA_ACTUAL) {
      case POSTGRESQL:
        return new PostgreSQLDAOFactory();
      default:
    	var mensajeUsuario = "Ha ocurrido un error. Por favor, contacte al administrador del sistema.";
    	var mensajeTecnico = "Tipo de factoria actual no soportada: " + FACTORIA_ACTUAL;
        throw new InmocontrolExcepcion(mensajeUsuario, mensajeTecnico);
    }
  }

  protected abstract void abrirConexion();

  public abstract void cerrarConexion();

  public abstract void iniciarTransaccion();

  public abstract void confirmarTransaccion();

  public abstract void cancelarTransaccion();

  public abstract PaisDAO obtenerPaisDAO();

  public abstract EstratoDAO obtenerEstratoDAO();

  public abstract TipoDocumentoDAO obtenerTipoDocumentoDAO();

  public abstract TipoPropiedadDAO obtenerTipoPropiedadDAO();

  public abstract TipoParticipanteDAO obtenerTipoParticipanteDAO();

  public abstract TipoAplicacionDAO obtenerTipoAplicacionDAO();

  public abstract ParametroDAO obtenerParametroDAO();

  public abstract AreaReferenciaDAO obtenerAreaReferenciaDAO();

  public abstract DepartamentoDAO obtenerDepartamentoDAO();

  public abstract CiudadDAO obtenerCiudadDAO();

  public abstract ClausulaContratoDAO obtenerClausulaContratoDAO();

  public abstract PersonaDAO obtenerPersonaDAO();

  public abstract ParticipanteContratoDAO obtenerParticipanteContratoDAO();

  public abstract PropiedadDAO obtenerPropiedadDAO();

  public abstract ParametroClausulaContratoDAO obtenerParametroClausulaContratoDAO();

  public abstract ContratoDAO obtenerContratoDAO();

  public abstract ClausulaPorContratoDAO obtenerClausulaPorContratoDAO();
}
