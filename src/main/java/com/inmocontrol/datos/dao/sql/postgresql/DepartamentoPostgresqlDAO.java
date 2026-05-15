package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.DepartamentoDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgresqlDAO extends SQLDAO implements DepartamentoDAO {

    public DepartamentoPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public DepartamentoEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre, pais_id FROM departamento WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el departamento por id.", e);
        }

        return null;
    }

    @Override
    public List<DepartamentoEntidad> consultarTodos() {
        String sql = "SELECT id, nombre, pais_id FROM departamento";
        List<DepartamentoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar los departamentos.", e);
        }

        return resultados;
    }

    @Override
    public List<DepartamentoEntidad> consultarTodosPorPais(UUID paisId) {
        String sql = "SELECT id, nombre, pais_id FROM departamento WHERE pais_id = ?";
        List<DepartamentoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, paisId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los departamentos por país.", e);
        }

        return resultados;
    }

    private DepartamentoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new DepartamentoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
