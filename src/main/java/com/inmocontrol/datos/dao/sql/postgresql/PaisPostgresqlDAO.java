package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PaisDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisPostgresqlDAO extends SQLDAO implements PaisDAO {

    public PaisPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public PaisEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre FROM pais WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar el país por id.", e);
        }

        return null;
    }

    @Override
    public List<PaisEntidad> consultarTodos() {
        String sql = "SELECT id, nombre FROM pais";
        List<PaisEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar los países.", e);
        }

        return resultados;
    }

    private PaisEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new PaisEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
