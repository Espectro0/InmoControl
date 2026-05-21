package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.AreaReferenciaDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
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
    String sql = "SELECT id, nombre FROM area_referencia WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el area referencia por id.", e);
    }

    return null;
  }

  @Override
  public List<AreaReferenciaEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM area_referencia";
    List<AreaReferenciaEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los areas referencia.", e);
    }

    return resultados;
  }

  @Override
  public List<AreaReferenciaEntidad> consultarPorFiltro(AreaReferenciaEntidad filtro) {
    String sql = "SELECT id, nombre FROM area_referencia WHERE 1=1";
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
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar areas referencia por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(AreaReferenciaEntidad entidad) {
    String sql = "INSERT INTO area_referencia (id, nombre) VALUES (?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setString(2, entidad.getNombre());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear el area referencia.", e);
    }
  }

  @Override
  public void actualizar(UUID id, AreaReferenciaEntidad entidad) {
    String sql = "UPDATE area_referencia SET nombre = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setString(1, entidad.getNombre());
      stmt.setObject(2, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar el area referencia.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM area_referencia WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar el area referencia.", e);
    }
  }

  private AreaReferenciaEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new AreaReferenciaEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .build();
  }
}
