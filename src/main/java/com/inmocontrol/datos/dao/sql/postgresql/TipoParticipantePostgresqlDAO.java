package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoParticipanteDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoParticipantePostgresqlDAO extends SQLDAO implements TipoParticipanteDAO {

    public TipoParticipantePostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public TipoParticipanteEntidad consultarPorId(UUID id) {
        String sql = "SELECT id, nombre FROM tipo_participante WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar el tipo de participante por id.", e);
        }

        return null;
    }

    @Override
    public List<TipoParticipanteEntidad> consultarTodos() {
        String sql = "SELECT id, nombre FROM tipo_participante";
        List<TipoParticipanteEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar los tipos de participante.", e);
        }

        return resultados;
    }

    private TipoParticipanteEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new TipoParticipanteEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
