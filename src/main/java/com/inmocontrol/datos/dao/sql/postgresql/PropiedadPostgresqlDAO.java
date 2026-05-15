package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PropiedadDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropiedadPostgresqlDAO extends SQLDAO implements PropiedadDAO {

    public PropiedadPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public PropiedadEntidad consultarPorId(UUID id) {
        String sql =
                "SELECT id, tipo_propiedad_id, estrato_id, nombre_inmueble, descripcion_inmueble, "
                        + "area_metros, direccion, ciudad_id FROM propiedad WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar la propiedad por id.", e);
        }

        return null;
    }

    @Override
    public List<PropiedadEntidad> consultarTodos() {
        String sql =
                "SELECT id, tipo_propiedad_id, estrato_id, nombre_inmueble, descripcion_inmueble, "
                        + "area_metros, direccion, ciudad_id FROM propiedad";
        List<PropiedadEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar las propiedades.", e);
        }

        return resultados;
    }

    @Override
    public PropiedadEntidad crear(PropiedadEntidad entidad) {
        String sql =
                "INSERT INTO propiedad (id, tipo_propiedad_id, estrato_id, nombre_inmueble, descripcion_inmueble, "
                        + "area_metros, direccion, ciudad_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(
                    2,
                    entidad.getTipoPropiedad() != null ? entidad.getTipoPropiedad().getId() : null);
            stmt.setObject(3, entidad.getEstrato() != null ? entidad.getEstrato().getId() : null);
            stmt.setString(4, entidad.getNombreInmueble());
            stmt.setString(5, entidad.getDescripcionInmueble());
            stmt.setObject(6, entidad.getAreaMetros());
            stmt.setString(7, entidad.getDireccion());
            stmt.setObject(8, entidad.getCiudad() != null ? entidad.getCiudad().getId() : null);
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al crear la propiedad.", e);
        }
    }

    @Override
    public PropiedadEntidad actualizar(PropiedadEntidad entidad) {
        String sql =
                "UPDATE propiedad SET tipo_propiedad_id = ?, estrato_id = ?, nombre_inmueble = ?, descripcion_inmueble = ?, "
                        + "area_metros = ?, direccion = ?, ciudad_id = ? WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(
                    1,
                    entidad.getTipoPropiedad() != null ? entidad.getTipoPropiedad().getId() : null);
            stmt.setObject(2, entidad.getEstrato() != null ? entidad.getEstrato().getId() : null);
            stmt.setString(3, entidad.getNombreInmueble());
            stmt.setString(4, entidad.getDescripcionInmueble());
            stmt.setObject(5, entidad.getAreaMetros());
            stmt.setString(6, entidad.getDireccion());
            stmt.setObject(7, entidad.getCiudad() != null ? entidad.getCiudad().getId() : null);
            stmt.setObject(8, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al actualizar la propiedad.", e);
        }
    }

    private PropiedadEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new PropiedadEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombreInmueble(rs.getString("nombre_inmueble"))
                .descripcionInmueble(rs.getString("descripcion_inmueble"))
                .areaMetros(rs.getObject("area_metros", Integer.class))
                .direccion(rs.getString("direccion"))
                .build();
    }
}
