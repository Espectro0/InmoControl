package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaPorContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClausulaPorContratoPostgresqlDAO extends SQLDAO implements ClausulaPorContratoDAO {

  public ClausulaPorContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ClausulaPorContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT id, numero_clausula, contrato_id, clausula_id "
            + "FROM clausula_por_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar la clausula por contrato por id.", e);
    }

    return null;
  }

  @Override
  public List<ClausulaPorContratoEntidad> consultarTodos() {
    String sql = "SELECT id, numero_clausula, contrato_id, clausula_id FROM clausula_por_contrato";
    List<ClausulaPorContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar las clausulas por contrato.", e);
    }

    return resultados;
  }

  @Override
  public List<ClausulaPorContratoEntidad> consultarPorFiltro(ClausulaPorContratoEntidad filtro) {
    String sql =
        "SELECT id, numero_clausula, contrato_id, clausula_id FROM clausula_por_contrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNumeroClausula() != null) {
      sql += " AND numero_clausula = ?";
      parametros.add(filtro.getNumeroClausula());
    }

    if (filtro.getContrato() != null && filtro.getContrato().getId() != null) {
      sql += " AND contrato_id = ?";
      parametros.add(filtro.getContrato().getId());
    }

    List<ClausulaPorContratoEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar clausulas por contrato por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(ClausulaPorContratoEntidad entidad) {
    String sql =
        "INSERT INTO clausula_por_contrato (id, numero_clausula, contrato_id, clausula_id) "
            + "VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(2, entidad.getNumeroClausula());
      stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(4, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear la clausula por contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ClausulaPorContratoEntidad entidad) {
    String sql =
        "UPDATE clausula_por_contrato SET numero_clausula = ?, contrato_id = ?, "
            + "clausula_id = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getNumeroClausula());
      stmt.setObject(2, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(3, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
      stmt.setObject(4, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar la clausula por contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM clausula_por_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar la clausula por contrato.", e);
    }
  }

  private ClausulaPorContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ClausulaPorContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .numeroClausula(rs.getObject("numero_clausula", Integer.class))
        .contrato(new ContratoEntidad.Builder().id(rs.getObject("contrato_id", UUID.class)).build())
        .clausula(
            new ClausulaContratoEntidad.Builder()
                .id(rs.getObject("clausula_id", UUID.class))
                .build())
        .build();
  }
}
