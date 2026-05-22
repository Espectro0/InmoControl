package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.CiudadDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgresqlDAO extends SQLDAO implements CiudadDAO {

  public CiudadPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public CiudadEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT c.id, c.nombre, c.departamento, d.nombre as departamento_nombre, d.pais, p.nombre as pais_nombre "
            + "FROM ciudad c "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais p ON d.pais = p.id "
            + "WHERE c.id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar la ciudad por id.", e);
    }

    return null;
  }

  @Override
  public List<CiudadEntidad> consultarTodos() {
    String sql =
        "SELECT c.id, c.nombre, c.departamento, d.nombre as departamento_nombre, d.pais, p.nombre as pais_nombre "
            + "FROM ciudad c "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais p ON d.pais = p.id";
    List<CiudadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar las ciudades.", e);
    }

    return resultados;
  }

  @Override
  public List<CiudadEntidad> consultarPorFiltro(CiudadEntidad filtro) {
    String sql =
        "SELECT c.id, c.nombre, c.departamento, d.nombre as departamento_nombre, d.pais, p.nombre as pais_nombre "
            + "FROM ciudad c "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais p ON d.pais = p.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND c.nombre = ?";
      parametros.add(filtro.getNombre());
    }

    if (filtro.getDepartamento() != null && filtro.getDepartamento().getId() != null) {
      sql += " AND c.departamento = ?";
      parametros.add(filtro.getDepartamento().getId());
    }

    List<CiudadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar ciudades por filtro.", e);
    }

    return resultados;
  }

  private CiudadEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new CiudadEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .departamento(
            new com.inmocontrol.entidad.DepartamentoEntidad.Builder()
                .id(rs.getObject("departamento", UUID.class))
                .nombre(rs.getString("departamento_nombre"))
                .pais(
                    new PaisEntidad.Builder()
                        .id(rs.getObject("pais", UUID.class))
                        .nombre(rs.getString("pais_nombre"))
                        .build())
                .build())
        .build();
  }
}
