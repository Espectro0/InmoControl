package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

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
        "SELECT c.id, c.areareferencia, c.tipoaplicacion, c.titulo, c.contenidolegal, "
            + "ar.nombre as ar_nombre, ta.nombre as ta_nombre "
            + "FROM clausulacontrato c "
            + "JOIN areareferencia ar ON c.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON c.tipoaplicacion = ta.id "
            + "WHERE c.id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.consultarPorId() - " + e.getMessage(),
          e);
    }

    return null;
  }

  @Override
  public List<ClausulaContratoEntidad> consultarTodos() {
    String sql =
        "SELECT c.id, c.areareferencia, c.tipoaplicacion, c.titulo, c.contenidolegal, "
            + "ar.nombre as ar_nombre, ta.nombre as ta_nombre "
            + "FROM clausulacontrato c "
            + "JOIN areareferencia ar ON c.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON c.tipoaplicacion = ta.id";
    List<ClausulaContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.consultarTodos() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public List<ClausulaContratoEntidad> consultarPorFiltro(ClausulaContratoEntidad filtro) {
    String sql =
        "SELECT c.id, c.areareferencia, c.tipoaplicacion, c.titulo, c.contenidolegal, "
            + "ar.nombre as ar_nombre, ta.nombre as ta_nombre "
            + "FROM clausulacontrato c "
            + "JOIN areareferencia ar ON c.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON c.tipoaplicacion = ta.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
      sql += " AND c.titulo = ?";
      parametros.add(filtro.getTitulo());
    }

    if (filtro.getAreaReferencia() != null && filtro.getAreaReferencia().getId() != null) {
      sql += " AND c.areareferencia = ?";
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
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public void crear(ClausulaContratoEntidad entidad) {
    String sql =
        "INSERT INTO clausulacontrato (id, areareferencia, tipoaplicacion, titulo, contenidolegal) "
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
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.crear() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void actualizar(UUID id, ClausulaContratoEntidad entidad) {
    String sql =
        "UPDATE clausulacontrato SET areareferencia = ?, tipoaplicacion = ?, "
            + "titulo = ?, contenidolegal = ? WHERE id = ?";

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
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.actualizar() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM clausulacontrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ClausulaContratoPostgresqlDAO.eliminar() - " + e.getMessage(),
          e);
    }
  }

  private ClausulaContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ClausulaContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .areaReferencia(
            new AreaReferenciaEntidad.Builder()
                .id(rs.getObject("areareferencia", UUID.class))
                .nombre(rs.getString("ar_nombre"))
                .build())
        .tipoAplicacion(
            new TipoAplicacionEntidad.Builder()
                .id(rs.getObject("tipoaplicacion", UUID.class))
                .nombre(rs.getString("ta_nombre"))
                .build())
        .titulo(rs.getString("titulo"))
        .contenidoLegal(rs.getString("contenidolegal"))
        .build();
  }
}

