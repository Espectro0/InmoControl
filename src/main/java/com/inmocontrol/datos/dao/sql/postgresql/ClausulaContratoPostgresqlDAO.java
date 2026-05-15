package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaContratoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
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
                "SELECT id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal FROM clausula_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar la cláusula de contrato por id.", e);
        }

        return null;
    }

    @Override
    public List<ClausulaContratoEntidad> consultarTodos() {
        String sql =
                "SELECT id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal FROM clausula_contrato";
        List<ClausulaContratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar las cláusulas de contrato.", e);
        }

        return resultados;
    }

    @Override
    public ClausulaContratoEntidad crear(ClausulaContratoEntidad entidad) {
        String sql =
                "INSERT INTO clausula_contrato (id, area_referencia_id, tipo_aplicacion_id, titulo, contenido_legal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(
                    2,
                    entidad.getAreaReferencia() != null
                            ? entidad.getAreaReferencia().getId()
                            : null);
            stmt.setObject(
                    3,
                    entidad.getTipoAplicacion() != null
                            ? entidad.getTipoAplicacion().getId()
                            : null);
            stmt.setString(4, entidad.getTitulo());
            stmt.setString(5, entidad.getContenidoLegal());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al crear la cláusula de contrato.", e);
        }
    }

    @Override
    public ClausulaContratoEntidad actualizar(ClausulaContratoEntidad entidad) {
        String sql =
                "UPDATE clausula_contrato SET area_referencia_id = ?, tipo_aplicacion_id = ?, titulo = ?, contenido_legal = ? WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(
                    1,
                    entidad.getAreaReferencia() != null
                            ? entidad.getAreaReferencia().getId()
                            : null);
            stmt.setObject(
                    2,
                    entidad.getTipoAplicacion() != null
                            ? entidad.getTipoAplicacion().getId()
                            : null);
            stmt.setString(3, entidad.getTitulo());
            stmt.setString(4, entidad.getContenidoLegal());
            stmt.setObject(5, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al actualizar la cláusula de contrato.", e);
        }
    }

    @Override
    public void eliminar(UUID id) {
        String sql = "DELETE FROM clausula_contrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al eliminar la cláusula de contrato.", e);
        }
    }

    private ClausulaContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ClausulaContratoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .titulo(rs.getString("titulo"))
                .contenidoLegal(rs.getString("contenido_legal"))
                .build();
    }
}
