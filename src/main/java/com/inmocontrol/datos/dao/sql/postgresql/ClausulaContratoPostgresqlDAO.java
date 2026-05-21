package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClausulaContratoPostgresqlDAO extends SQLDAO implements ClausulaContratoDAO {

  public ClausulaContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ClausulaContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal "
            + "FROM clausula_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar la clausula contrato por id.", e);
    }

    return null;
  }

  @Override
  public List<ClausulaContratoEntidad> consultarTodos() {
    String sql =
        "SELECT id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal "
            + "FROM clausula_contrato";
    List<ClausulaContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar las clausulas contrato.", e);
    }

    return resultados;
  }

  @Override
  public List<ClausulaContratoEntidad> consultarPorFiltro(ClausulaContratoEntidad filtro) {
    String sql =
        "SELECT id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal "
            + "FROM clausula_contrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
      sql += " AND titulo = ?";
      parametros.add(filtro.getTitulo());
    }

    if (filtro.getAreaReferencia() != null && filtro.getAreaReferencia().getId() != null) {
      sql += " AND area_referencia_id = ?";
      parametros.add(filtro.getAreaReferencia().getId());
    }

    List<ClausulaContratoEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar clausulas contrato por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(ClausulaContratoEntidad entidad) {
    String sql =
        "INSERT INTO clausula_contrato (id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal) "
            + "VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(
          2, entidad.getAreaReferencia() != null ? entidad.getAreaReferencia().getId() : null);
      stmt.setObject(
          3, entidad.getTipoAplicacion() != null ? entidad.getTipoAplicacion().getId() : null);
      stmt.setString(4, entidad.getTitulo());
      stmt.setString(5, entidad.getContenidoLegal());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear la clausula contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ClausulaContratoEntidad entidad) {
    String sql =
        "UPDATE clausula_contrato SET area_referencia_id = ?, tipo_aplicacion_id = ?, "
            + "titulo = ?, contenido_legal = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(
          1, entidad.getAreaReferencia() != null ? entidad.getAreaReferencia().getId() : null);
      stmt.setObject(
          2, entidad.getTipoAplicacion() != null ? entidad.getTipoAplicacion().getId() : null);
      stmt.setString(3, entidad.getTitulo());
      stmt.setString(4, entidad.getContenidoLegal());
      stmt.setObject(5, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar la clausula contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM clausula_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar la clausula contrato.", e);
    }
  }

  private ClausulaContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ClausulaContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .areaReferencia(
            new AreaReferenciaEntidad.Builder()
                .id(rs.getObject("area_referencia_id", UUID.class))
                .build())
        .tipoAplicacion(
            new TipoAplicacionEntidad.Builder()
                .id(rs.getObject("tipo_aplicacion_id", UUID.class))
                .build())
        .titulo(rs.getString("titulo"))
        .contenidoLegal(rs.getString("contenido_legal"))
        .build();
  }
}
