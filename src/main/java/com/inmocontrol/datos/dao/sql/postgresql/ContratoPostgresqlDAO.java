package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ContratoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
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
                "SELECT id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad_id "
                        + "FROM contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar el contrato por id.", e);
        }

        return null;
    }

    @Override
    public List<ContratoEntidad> consultarTodos() {
        String sql =
                "SELECT id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad_id FROM contrato";
        List<ContratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar los contratos.", e);
        }

        return resultados;
    }

    @Override
    public ContratoEntidad crear(ContratoEntidad entidad) {
        String sql =
                "INSERT INTO contrato (id, codigo_contrato, fecha_inicio, fecha_fin, es_activo, propiedad_id) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setString(2, entidad.getCodigoContrato());
            stmt.setDate(
                    3,
                    entidad.getFechaInicio() != null
                            ? new java.sql.Date(entidad.getFechaInicio().getTime())
                            : null);
            stmt.setDate(
                    4,
                    entidad.getFechaFin() != null
                            ? new java.sql.Date(entidad.getFechaFin().getTime())
                            : null);
            stmt.setObject(5, entidad.getEsActivo());
            stmt.setObject(
                    6, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al crear el contrato.", e);
        }
    }

    @Override
    public ContratoEntidad actualizar(ContratoEntidad entidad) {
        String sql =
                "UPDATE contrato SET codigo_contrato = ?, fecha_inicio = ?, fecha_fin = ?, es_activo = ?, propiedad_id = ? "
                        + "WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setString(1, entidad.getCodigoContrato());
            stmt.setDate(
                    2,
                    entidad.getFechaInicio() != null
                            ? new java.sql.Date(entidad.getFechaInicio().getTime())
                            : null);
            stmt.setDate(
                    3,
                    entidad.getFechaFin() != null
                            ? new java.sql.Date(entidad.getFechaFin().getTime())
                            : null);
            stmt.setObject(4, entidad.getEsActivo());
            stmt.setObject(
                    5, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
            stmt.setObject(6, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al actualizar el contrato.", e);
        }
    }

    @Override
    public void actualizarEstado(UUID id, boolean activo) {
        String sql = "UPDATE contrato SET es_activo = ? WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, activo);
            stmt.setObject(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al actualizar el estado del contrato.", e);
        }
    }

    private ContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ContratoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .codigoContrato(rs.getString("codigo_contrato"))
                .fechaInicio(rs.getDate("fecha_inicio"))
                .fechaFin(rs.getDate("fecha_fin"))
                .esActivo(rs.getObject("es_activo", Boolean.class))
                .build();
    }
}
