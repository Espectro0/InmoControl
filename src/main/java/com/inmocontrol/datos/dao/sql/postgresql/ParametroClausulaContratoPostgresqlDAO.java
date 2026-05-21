package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParametroClausulaContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParametroClausulaContratoPostgresqlDAO extends SQLDAO
    implements ParametroClausulaContratoDAO {

  public ParametroClausulaContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ParametroClausulaContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT id, parametro_id, clausula_por_contrato_id, valor "
            + "FROM parametro_clausula_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar el parametro clausula contrato por id.", e);
    }

    return null;
  }

  @Override
  public List<ParametroClausulaContratoEntidad> consultarTodos() {
    String sql =
        "SELECT id, parametro_id, clausula_por_contrato_id, valor FROM parametro_clausula_contrato";
    List<ParametroClausulaContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar los parametros clausula contrato.", e);
    }

    return resultados;
  }

  @Override
  public List<ParametroClausulaContratoEntidad> consultarPorFiltro(
      ParametroClausulaContratoEntidad filtro) {
    String sql =
        "SELECT id, parametro_id, clausula_por_contrato_id, valor "
            + "FROM parametro_clausula_contrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getParametro() != null && filtro.getParametro().getId() != null) {
      sql += " AND parametro_id = ?";
      parametros.add(filtro.getParametro().getId());
    }

    if (filtro.getClausulaPorContrato() != null
        && filtro.getClausulaPorContrato().getId() != null) {
      sql += " AND clausula_por_contrato_id = ?";
      parametros.add(filtro.getClausulaPorContrato().getId());
    }

    List<ParametroClausulaContratoEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar parametros clausula contrato por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(ParametroClausulaContratoEntidad entidad) {
    String sql =
        "INSERT INTO parametro_clausula_contrato (id, parametro_id, clausula_por_contrato_id, valor) "
            + "VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(2, entidad.getParametro() != null ? entidad.getParametro().getId() : null);
      stmt.setObject(
          3,
          entidad.getClausulaPorContrato() != null
              ? entidad.getClausulaPorContrato().getId()
              : null);
      stmt.setString(4, entidad.getValor());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al crear el parametro clausula contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ParametroClausulaContratoEntidad entidad) {
    String sql =
        "UPDATE parametro_clausula_contrato SET parametro_id = ?, clausula_por_contrato_id = ?, "
            + "valor = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getParametro() != null ? entidad.getParametro().getId() : null);
      stmt.setObject(
          2,
          entidad.getClausulaPorContrato() != null
              ? entidad.getClausulaPorContrato().getId()
              : null);
      stmt.setString(3, entidad.getValor());
      stmt.setObject(4, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al actualizar el parametro clausula contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM parametro_clausula_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al eliminar el parametro clausula contrato.", e);
    }
  }

  private ParametroClausulaContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ParametroClausulaContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .parametro(
            new ParametroEntidad.Builder().id(rs.getObject("parametro_id", UUID.class)).build())
        .clausulaPorContrato(
            new ClausulaPorContratoEntidad.Builder()
                .id(rs.getObject("clausula_por_contrato_id", UUID.class))
                .build())
        .valor(rs.getString("valor"))
        .build();
  }
}
