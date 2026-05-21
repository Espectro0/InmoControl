package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParticipanteContratoPostgresqlDAO extends SQLDAO implements ParticipanteContratoDAO {

  public ParticipanteContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ParticipanteContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT id, persona, tipo_participante, contrato "
            + "FROM participante_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar el participante contrato por id.", e);
    }

    return null;
  }

  @Override
  public void crear(ParticipanteContratoEntidad entidad) {
    String sql =
        "INSERT INTO participante_contrato (id, persona, tipo_participante, contrato) "
            + "VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(2, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
      stmt.setObject(
          3, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
      stmt.setObject(4, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear el participante contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ParticipanteContratoEntidad entidad) {
    String sql =
        "UPDATE participante_contrato SET persona = ?, tipo_participante = ?, "
            + "contrato = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
      stmt.setObject(
          2, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
      stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(4, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar el participante contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM participante_contrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar el participante contrato.", e);
    }
  }

  @Override
  public List<ParticipanteContratoEntidad> consultarTodos() {
    String sql = "SELECT id, persona, tipo_participante, contrato FROM participante_contrato";
    List<ParticipanteContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar los participantes contrato.", e);
    }

    return resultados;
  }

  @Override
  public List<ParticipanteContratoEntidad> consultarPorFiltro(ParticipanteContratoEntidad filtro) {
    String sql =
        "SELECT id, persona, tipo_participante, contrato FROM participante_contrato WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getPersona() != null && filtro.getPersona().getId() != null) {
      sql += " AND persona = ?";
      parametros.add(filtro.getPersona().getId());
    }

    if (filtro.getTipoParticipante() != null && filtro.getTipoParticipante().getId() != null) {
      sql += " AND tipo_participante = ?";
      parametros.add(filtro.getTipoParticipante().getId());
    }

    if (filtro.getContrato() != null && filtro.getContrato().getId() != null) {
      sql += " AND contrato = ?";
      parametros.add(filtro.getContrato().getId());
    }

    List<ParticipanteContratoEntidad> resultados = new ArrayList<>();

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
          "Ocurrio un error al consultar participantes contrato por filtro.", e);
    }

    return resultados;
  }

  private ParticipanteContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ParticipanteContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .persona(new PersonaEntidad.Builder().id(rs.getObject("persona", UUID.class)).build())
        .tipoParticipante(
            new TipoParticipanteEntidad.Builder()
                .id(rs.getObject("tipo_participante", UUID.class))
                .build())
        .contrato(new ContratoEntidad.Builder().id(rs.getObject("contrato", UUID.class)).build())
        .build();
  }
}
