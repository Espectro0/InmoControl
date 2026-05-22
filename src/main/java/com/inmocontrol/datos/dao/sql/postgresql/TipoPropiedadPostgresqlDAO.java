package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoPropiedadDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoPropiedadPostgresqlDAO extends SQLDAO implements TipoPropiedadDAO {

  public TipoPropiedadPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public TipoPropiedadEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre FROM tipopropiedad WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el tipo propiedad por id.", e);
    }

    return null;
  }

  @Override
  public List<TipoPropiedadEntidad> consultarTodos() {
    String sql = "SELECT id, nombre FROM tipopropiedad";
    List<TipoPropiedadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los tipos propiedad.", e);
    }

    return resultados;
  }

  @Override
  public List<TipoPropiedadEntidad> consultarPorFiltro(TipoPropiedadEntidad filtro) {
    String sql = "SELECT id, nombre FROM tipopropiedad WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    List<TipoPropiedadEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar tipos propiedad por filtro.", e);
    }

    return resultados;
  }

  private TipoPropiedadEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new TipoPropiedadEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .build();
  }
}
