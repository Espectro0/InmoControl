package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParametroDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParametroPostgresqlDAO extends SQLDAO implements ParametroDAO {

    public ParametroPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public ParametroEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, placeholder, descripcion FROM parametro WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar el parámetro por id.", e);
        }

        return null;
    }

    @Override
    public List<ParametroEntidad> consultarTodos() {
        String sql = "SELECT id, placeholder, descripcion FROM parametro";
        List<ParametroEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar los parámetros.", e);
        }

        return resultados;
    }

    private ParametroEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new ParametroEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .placeholder(rs.getString("placeholder"))
                .descripcion(rs.getString("descripcion"))
                .build();
    }
}
