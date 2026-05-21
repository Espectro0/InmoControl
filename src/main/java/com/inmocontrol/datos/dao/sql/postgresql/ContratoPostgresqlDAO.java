package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratoPostgresqlDAO extends SQLDAO implements ContratoDAO {

  public ContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad "
            + "FROM contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar el contrato por id.", e);
    }

    return null;
  }

  @Override
  public List<ContratoEntidad> consultarTodos() {
    String sql =
        "SELECT id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad FROM contrato";
    List<ContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar los contratos.", e);
    }

    return resultados;
  }

  @Override
  public List<ContratoEntidad> consultarPorFiltro(ContratoEntidad filtro) {
    String sql =
        "SELECT id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad "
            + "FROM contrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getCodigoContrato() != null && !filtro.getCodigoContrato().isEmpty()) {
      sql += " AND codigo_contrato = ?";
      parametros.add(filtro.getCodigoContrato());
    }

    if (filtro.getEsActivo() != null) {
      sql += " AND es_activo = ?";
      parametros.add(filtro.getEsActivo());
    }

    if (filtro.getPropiedad() != null && filtro.getPropiedad().getId() != null) {
      sql += " AND propiedad = ?";
      parametros.add(filtro.getPropiedad().getId());
    }

    if (filtro.getFechaInicio() != null) {
      sql += " AND fecha_inicio >= ?";
      parametros.add(new Date(filtro.getFechaInicio().getTime()));
    }

    if (filtro.getFechaFin() != null) {
      sql += " AND fecha_fin <= ?";
      parametros.add(new Date(filtro.getFechaFin().getTime()));
    }

    List<ContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar contratos por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(ContratoEntidad entidad) {
    String sql =
        "INSERT INTO contrato (id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setString(2, entidad.getCodigoContrato());
      stmt.setDate(
          3,
          entidad.getFechaInicio() != null ? new Date(entidad.getFechaInicio().getTime()) : null);
      stmt.setDate(
          4, entidad.getFechaFin() != null ? new Date(entidad.getFechaFin().getTime()) : null);
      stmt.setObject(5, entidad.getEsActivo());
      stmt.setObject(6, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear el contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ContratoEntidad entidad) {
    String sql =
        "UPDATE contrato SET codigo_contrato = ?, fecha_inicio = ?, fecha_fin = ?, "
            + "es_activo = ?, propiedad = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setString(1, entidad.getCodigoContrato());
      stmt.setDate(
          2,
          entidad.getFechaInicio() != null ? new Date(entidad.getFechaInicio().getTime()) : null);
      stmt.setDate(
          3, entidad.getFechaFin() != null ? new Date(entidad.getFechaFin().getTime()) : null);
      stmt.setObject(4, entidad.getEsActivo());
      stmt.setObject(5, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
      stmt.setObject(6, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar el contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar el contrato.", e);
    }
  }

  private ContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .codigoContrato(rs.getString("codigo_contrato"))
        .fechaInicio(rs.getDate("fecha_inicio"))
        .fechaFin(rs.getDate("fecha_fin"))
        .esActivo(rs.getObject("es_activo", Boolean.class))
        .propiedad(new PropiedadEntidad.Builder().id(rs.getObject("propiedad", UUID.class)).build())
        .build();
  }
}
