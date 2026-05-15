package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParametroClausulaContratoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
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
                "SELECT id, parametro_id, clausula_por_contrato_id, valor FROM parametro_clausula_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el parámetro de cláusula de contrato por id.",
                    e);
        }

        return null;
    }

    @Override
    public List<ParametroClausulaContratoEntidad> consultarTodosPorClausulaPorContrato(
            UUID clausulaPorContratoId) {
        String sql =
                "SELECT id, parametro_id, clausula_por_contrato_id, valor FROM parametro_clausula_contrato WHERE clausula_por_contrato_id = ?";
        List<ParametroClausulaContratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, clausulaPorContratoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los parámetros de cláusula de contrato.", e);
        }

        return resultados;
    }

    @Override
    public ParametroClausulaContratoEntidad crear(ParametroClausulaContratoEntidad entidad) {
        String sql =
                "INSERT INTO parametro_clausula_contrato (id, parametro_id, clausula_por_contrato_id, valor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(
                    2, entidad.getParametro() != null ? entidad.getParametro().getId() : null);
            stmt.setObject(
                    3,
                    entidad.getClausulaPorContrato() != null
                            ? entidad.getClausulaPorContrato().getId()
                            : null);
            stmt.setString(4, entidad.getValor());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al crear el parámetro de cláusula de contrato.", e);
        }
    }

    @Override
    public ParametroClausulaContratoEntidad actualizar(ParametroClausulaContratoEntidad entidad) {
        String sql =
                "UPDATE parametro_clausula_contrato SET parametro_id = ?, clausula_por_contrato_id = ?, valor = ? WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(
                    1, entidad.getParametro() != null ? entidad.getParametro().getId() : null);
            stmt.setObject(
                    2,
                    entidad.getClausulaPorContrato() != null
                            ? entidad.getClausulaPorContrato().getId()
                            : null);
            stmt.setString(3, entidad.getValor());
            stmt.setObject(4, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al actualizar el parámetro de cláusula de contrato.", e);
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
                    "Ocurrió un error al eliminar el parámetro de cláusula de contrato.", e);
        }
    }

    private ParametroClausulaContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ParametroClausulaContratoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .valor(rs.getString("valor"))
                .build();
    }
}
