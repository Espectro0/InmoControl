package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoPropiedadDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoPropiedadPostgresqlDAO extends SQLDAO implements TipoPropiedadDAO {

    public TipoPropiedadPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public TipoPropiedadEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre FROM tipo_propiedad WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el tipo de propiedad por id.", e);
        }

        return null;
    }

    @Override
    public List<TipoPropiedadEntidad> consultarTodos() {
        String sql = "SELECT id, nombre FROM tipo_propiedad";
        List<TipoPropiedadEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los tipos de propiedad.", e);
        }

        return resultados;
    }

    private TipoPropiedadEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new TipoPropiedadEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
