package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PaisDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisPostgresqlDAO extends SQLDAO implements PaisDAO {

  public PaisPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public PaisEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre FROM pais WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return new PaisEntidad.Builder()
            .id(rs.getObject("id", UUID.class))
            .nombre(rs.getString("nombre"))
            .build();
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el pais por id.", e);
    }

    return null;
  }

  @Override
  public List<PaisEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM pais";
    List<PaisEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(
            new PaisEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build());
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los paises.", e);
    }

    return resultados;
  }

  @Override
  public List<PaisEntidad> consultarPorFiltro(PaisEntidad filtro) {
    String sql = "SELECT id, nombre FROM pais WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    List<PaisEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(
            new PaisEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build());
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar paises por filtro.", e);
    }

    return resultados;
  }
}
