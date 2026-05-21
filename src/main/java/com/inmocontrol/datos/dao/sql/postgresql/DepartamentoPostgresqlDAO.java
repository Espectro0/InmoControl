package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.DepartamentoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgresqlDAO extends SQLDAO implements DepartamentoDAO {

  public DepartamentoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public DepartamentoEntidad consultarPorId(UUID id) {
    String sql = "SELECT id, nombre, pais FROM departamento WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el departamento por id.", e);
    }

    return null;
  }

  @Override
  public List<DepartamentoEntidad> consultarTodos() {
    String sql = "SELECT id, nombre, pais FROM departamento";
    List<DepartamentoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los departamentos.", e);
    }

    return resultados;
  }

  @Override
  public List<DepartamentoEntidad> consultarPorFiltro(DepartamentoEntidad filtro) {
    String sql = "SELECT id, nombre, pais FROM departamento WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
      sql += " AND nombre = ?";
      parametros.add(filtro.getNombre());
    }

    if (filtro.getPais() != null && filtro.getPais().getId() != null) {
      sql += " AND pais = ?";
      parametros.add(filtro.getPais().getId());
    }

    List<DepartamentoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar departamentos por filtro.", e);
    }

    return resultados;
  }

  private DepartamentoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new DepartamentoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .nombre(rs.getString("nombre"))
        .pais(
            new com.inmocontrol.entidad.PaisEntidad.Builder()
                .id(rs.getObject("pais", UUID.class))
                .build())
        .build();
  }
}
