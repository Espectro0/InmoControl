package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoDocumentoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoDocumentoPostgresqlDAO extends SQLDAO implements TipoDocumentoDAO {

  public TipoDocumentoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public TipoDocumentoEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre FROM tipodocumento WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en TipoDocumentoPostgresqlDAO.consultarPorId() - " + e.getMessage(),
          e);
    }

    return null;
  }

  @Override
  public List<TipoDocumentoEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM tipodocumento";
    List<TipoDocumentoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en TipoDocumentoPostgresqlDAO.consultarTodos() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public List<TipoDocumentoEntidad> consultarPorFiltro(TipoDocumentoEntidad filtro) {
    String sql = "SELECT id, nombre FROM tipodocumento WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    List<TipoDocumentoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en TipoDocumentoPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  private TipoDocumentoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new TipoDocumentoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .build();
  }
}

