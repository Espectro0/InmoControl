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
        "SELECT id, numeroclausula, contrato, clausula " + "FROM clausulaporcontrato WHERE id = ?";

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
    String sql = "SELECT id, numeroclausula, contrato, clausula FROM clausulaporcontrato";
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
    String sql = "SELECT id, numeroclausula, contrato, clausula FROM clausulaporcontrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNumeroClausula() != null) {
      sql += " AND numeroclausula = ?";
      parametros.add(filtro.getNumeroClausula());
    }

    if (filtro.getContrato() != null && filtro.getContrato().getId() != null) {
      sql += " AND contrato = ?";
      parametros.add(filtro.getContrato().getId());
    }

    if (filtro.getClausula() != null && filtro.getClausula().getId() != null) {
      sql += " AND clausula = ?";
      parametros.add(filtro.getClausula().getId());
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
        "INSERT INTO clausulaporcontrato (id, numeroclausula, contrato, clausula) "
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
        "UPDATE clausulaporcontrato SET numeroclausula = ?, contrato = ?, "
            + "clausula = ? WHERE id = ?";

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
    String sql = "DELETE FROM clausulaporcontrato WHERE id = ?";

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
        .numeroClausula(rs.getObject("numeroclausula", Integer.class))
        .contrato(new ContratoEntidad.Builder().id(rs.getObject("contrato", UUID.class)).build())
        .clausula(
            new ClausulaContratoEntidad.Builder().id(rs.getObject("clausula", UUID.class)).build())
        .build();
  }
}
