package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaPorContratoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
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
                "SELECT id, numero_clausula, contrato_id, clausula_id FROM clausula_por_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar la cláusula por contrato por id.", e);
        }

        return null;
    }

    @Override
    public List<ClausulaPorContratoEntidad> consultarTodosPorContrato(UUID contratoId) {
        String sql =
                "SELECT id, numero_clausula, contrato_id, clausula_id FROM clausula_por_contrato WHERE contrato_id = ?";
        List<ClausulaPorContratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, contratoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar las cláusulas por contrato.", e);
        }

        return resultados;
    }

    @Override
    public ClausulaPorContratoEntidad crear(ClausulaPorContratoEntidad entidad) {
        String sql =
                "INSERT INTO clausula_por_contrato (id, numero_clausula, contrato_id, clausula_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(2, entidad.getNumeroClausula());
            stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
            stmt.setObject(4, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al crear la cláusula por contrato.", e);
        }
    }

    @Override
    public ClausulaPorContratoEntidad actualizar(ClausulaPorContratoEntidad entidad) {
        String sql =
                "UPDATE clausula_por_contrato SET numero_clausula = ?, contrato_id = ?, clausula_id = ? WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getNumeroClausula());
            stmt.setObject(2, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
            stmt.setObject(3, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
            stmt.setObject(4, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al actualizar la cláusula por contrato.", e);
        }
    }

    @Override
    public void eliminar(UUID id) {
        String sql = "DELETE FROM clausula_por_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al eliminar la cláusula por contrato.", e);
        }
    }

    private ClausulaPorContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ClausulaPorContratoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .numeroClausula(rs.getObject("numero_clausula", Integer.class))
                .build();
    }
}
