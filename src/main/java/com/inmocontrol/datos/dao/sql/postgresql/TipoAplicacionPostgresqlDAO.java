package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoAplicacionDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoAplicacionPostgresqlDAO extends SQLDAO implements TipoAplicacionDAO {

    public TipoAplicacionPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public TipoAplicacionEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre FROM tipo_aplicacion WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el tipo de aplicación por id.", e);
        }

        return null;
    }

    @Override
    public List<TipoAplicacionEntidad> consultarTodos() {
        String sql = "SELECT id, nombre FROM tipo_aplicacion";
        List<TipoAplicacionEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los tipos de aplicación.", e);
        }

        return resultados;
    }

    private TipoAplicacionEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new TipoAplicacionEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
