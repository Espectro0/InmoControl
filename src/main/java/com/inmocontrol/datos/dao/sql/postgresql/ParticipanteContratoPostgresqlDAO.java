package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
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
                "SELECT id, persona_id, tipo_participante_id, contrato_id FROM participante_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el participante de contrato por id.", e);
        }

        return null;
    }

    @Override
    public List<ParticipanteContratoEntidad> consultarTodosPorContrato(UUID contratoId) {
        String sql =
                "SELECT id, persona_id, tipo_participante_id, contrato_id FROM participante_contrato WHERE contrato_id = ?";
        List<ParticipanteContratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, contratoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los participantes de contrato.", e);
        }

        return resultados;
    }

    @Override
    public ParticipanteContratoEntidad crear(ParticipanteContratoEntidad entidad) {
        String sql =
                "INSERT INTO participante_contrato (id, persona_id, tipo_participante_id, contrato_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(2, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
            stmt.setObject(
                    3,
                    entidad.getTipoParticipante() != null
                            ? entidad.getTipoParticipante().getId()
                            : null);
            stmt.setObject(4, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al crear el participante de contrato.", e);
        }
    }

    @Override
    public void eliminar(UUID id) {
        String sql = "DELETE FROM participante_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al eliminar el participante de contrato.", e);
        }
    }

    private ParticipanteContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ParticipanteContratoEntidad.Builder().id(rs.getObject("id", UUID.class)).build();
    }
}
