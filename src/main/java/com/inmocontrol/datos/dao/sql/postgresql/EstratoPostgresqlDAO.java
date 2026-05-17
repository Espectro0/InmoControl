package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.EstratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstratoPostgresqlDAO extends SQLDAO implements EstratoDAO {

    public EstratoPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public EstratoEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre, descripcion FROM estrato WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrio un error al consultar el estrato por id.", e);
        }

        return null;
    }

    @Override
    public List<EstratoEntidad> consultarTodos() {
        String sql = "SELECT id, nombre, descripcion FROM estrato";
        List<EstratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrio un error al consultar los estratos.", e);
        }

        return resultados;
    }

    @Override
    public List<EstratoEntidad> consultarPorFiltro(EstratoEntidad filtro) {
        String sql = "SELECT id, nombre, descripcion FROM estrato WHERE 1=1";
        List<Object> parametros = new ArrayList<>();

        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
            sql += " AND nombre = ?";
            parametros.add(filtro.getNombre());
        }

        if (filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty()) {
            sql += " AND descripcion = ?";
            parametros.add(filtro.getDescripcion());
        }

        List<EstratoEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrio un error al consultar estratos por filtro.", e);
        }

        return resultados;
    }

    private EstratoEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new EstratoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .descripcion(rs.getString("descripcion"))
                .build();
    }
}