package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.CiudadDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgresqlDAO extends SQLDAO implements CiudadDAO {

    public CiudadPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public CiudadEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre, departamento_id FROM ciudad WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar la ciudad por id.", e);
        }

        return null;
    }

    @Override
    public List<CiudadEntidad> consultarTodos() {
        String sql = "SELECT id, nombre, departamento_id FROM ciudad";
        List<CiudadEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar las ciudades.", e);
        }

        return resultados;
    }

    @Override
    public List<CiudadEntidad> consultarTodosPorDepartamento(UUID departamentoId) {
        String sql = "SELECT id, nombre, departamento_id FROM ciudad WHERE departamento_id = ?";
        List<CiudadEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, departamentoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar las ciudades por departamento.", e);
        }

        return resultados;
    }

    private CiudadEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new CiudadEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
