package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.AreaReferenciaDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AreaReferenciaPostgresqlDAO extends SQLDAO implements AreaReferenciaDAO {

  public AreaReferenciaPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public AreaReferenciaEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre FROM areareferencia WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en AreaReferenciaPostgresqlDAO.consultarPorId() - " + e.getMessage(),
          e);
    }

    return null;
  }

  @Override
  public List<AreaReferenciaEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM areareferencia";
    List<AreaReferenciaEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en AreaReferenciaPostgresqlDAO.consultarTodos() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public List<AreaReferenciaEntidad> consultarPorFiltro(AreaReferenciaEntidad filtro) {
    String sql = "SELECT id, nombre FROM areareferencia WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    List<AreaReferenciaEntidad> resultados = new ArrayList<>();

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
          "Error en AreaReferenciaPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public void crear(AreaReferenciaEntidad entidad) {
    String sql = "INSERT INTO areareferencia (id, nombre) VALUES (?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setString(2, entidad.getNombre());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en AreaReferenciaPostgresqlDAO.crear() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void actualizar(UUID id, AreaReferenciaEntidad entidad) {
    String sql = "UPDATE areareferencia SET nombre = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setString(1, entidad.getNombre());
      stmt.setObject(2, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en AreaReferenciaPostgresqlDAO.actualizar() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM areareferencia WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en AreaReferenciaPostgresqlDAO.eliminar() - " + e.getMessage(),
          e);
    }
  }

  private AreaReferenciaEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new AreaReferenciaEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .build();
  }
}

