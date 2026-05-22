package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoAplicacionDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoAplicacionPostgresqlDAO extends SQLDAO implements TipoAplicacionDAO {

  public TipoAplicacionPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public TipoAplicacionEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre FROM tipoaplicacion WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el tipo aplicacion por id.", e);
    }

    return null;
  }

  @Override
  public List<TipoAplicacionEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM tipoaplicacion";
    List<TipoAplicacionEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los tipos aplicacion.", e);
    }

    return resultados;
  }

  @Override
  public List<TipoAplicacionEntidad> consultarPorFiltro(TipoAplicacionEntidad filtro) {
    String sql = "SELECT id, nombre FROM tipoaplicacion WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    List<TipoAplicacionEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar tipos aplicacion por filtro.", e);
    }

    return resultados;
  }

  private TipoAplicacionEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new TipoAplicacionEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .build();
  }
}
