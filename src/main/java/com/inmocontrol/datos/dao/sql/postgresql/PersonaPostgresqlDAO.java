package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PersonaDAO;
import com.inmocontrol.datos.sql.SQLDAO;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonaPostgresqlDAO extends SQLDAO implements PersonaDAO {

    public PersonaPostgresqlDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public PersonaEntidad consultarPorId(UUID id) {
        String sql =
                "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, numero_telefonico, correo_electronico, "
                        + "direccion_residencia, ciudad_residencia_id, fecha_nacimiento, edad "
                        + "FROM persona WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar la persona por id.", e);
        }

        return null;
    }

    @Override
    public List<PersonaEntidad> consultarTodos() {
        String sql =
                "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, numero_telefonico, correo_electronico, "
                        + "direccion_residencia, ciudad_residencia_id, fecha_nacimiento, edad "
                        + "FROM persona";
        List<PersonaEntidad> resultados = new ArrayList<>();

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.add(mapearResultado(rs));
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al consultar las personas.", e);
        }

        return resultados;
    }

    @Override
    public PersonaEntidad consultarPorNumeroIdentificacion(String numero) {
        String sql =
                "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, numero_telefonico, correo_electronico, "
                        + "direccion_residencia, ciudad_residencia_id, fecha_nacimiento, edad "
                        + "FROM persona WHERE numero_identificacion = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearResultado(rs);
            }
        } catch (SQLException e) {
            throw new TransaccionExcepcion(
                    "Ocurrió un error al consultar la persona por número de identificación.", e);
        }

        return null;
    }

    @Override
    public PersonaEntidad crear(PersonaEntidad entidad) {
        String sql =
                "INSERT INTO persona (id, tipo_documento_id, numero_identificacion, primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, numero_telefonico, correo_electronico, "
                        + "direccion_residencia, ciudad_residencia_id, fecha_nacimiento, edad) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(1, entidad.getId());
            stmt.setObject(
                    2,
                    entidad.getTipoDocumento() != null ? entidad.getTipoDocumento().getId() : null);
            stmt.setString(3, entidad.getNumeroIdentificacion());
            stmt.setString(4, entidad.getPrimerNombre());
            stmt.setString(5, entidad.getSegundoNombre());
            stmt.setString(6, entidad.getPrimerApellido());
            stmt.setString(7, entidad.getSegundoApellido());
            stmt.setString(8, entidad.getNumeroTelefonico());
            stmt.setString(9, entidad.getCorreoElectronico());
            stmt.setString(10, entidad.getDireccionResidencia());
            stmt.setObject(
                    11,
                    entidad.getCiudadResidencia() != null
                            ? entidad.getCiudadResidencia().getId()
                            : null);
            stmt.setDate(
                    12,
                    entidad.getFechaNacimiento() != null
                            ? new java.sql.Date(entidad.getFechaNacimiento().getTime())
                            : null);
            stmt.setObject(13, entidad.getEdad());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al crear la persona.", e);
        }
    }

    @Override
    public PersonaEntidad actualizar(PersonaEntidad entidad) {
        String sql =
                "UPDATE persona SET tipo_documento_id = ?, numero_identificacion = ?, primer_nombre = ?, segundo_nombre = ?, "
                        + "primer_apellido = ?, segundo_apellido = ?, numero_telefonico = ?, correo_electronico = ?, "
                        + "direccion_residencia = ?, ciudad_residencia_id = ?, fecha_nacimiento = ?, edad = ? "
                        + "WHERE id = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
            stmt.setObject(
                    1,
                    entidad.getTipoDocumento() != null ? entidad.getTipoDocumento().getId() : null);
            stmt.setString(2, entidad.getNumeroIdentificacion());
            stmt.setString(3, entidad.getPrimerNombre());
            stmt.setString(4, entidad.getSegundoNombre());
            stmt.setString(5, entidad.getPrimerApellido());
            stmt.setString(6, entidad.getSegundoApellido());
            stmt.setString(7, entidad.getNumeroTelefonico());
            stmt.setString(8, entidad.getCorreoElectronico());
            stmt.setString(9, entidad.getDireccionResidencia());
            stmt.setObject(
                    10,
                    entidad.getCiudadResidencia() != null
                            ? entidad.getCiudadResidencia().getId()
                            : null);
            stmt.setDate(
                    11,
                    entidad.getFechaNacimiento() != null
                            ? new java.sql.Date(entidad.getFechaNacimiento().getTime())
                            : null);
            stmt.setObject(12, entidad.getEdad());
            stmt.setObject(13, entidad.getId());
            stmt.executeUpdate();
            return entidad;
        } catch (SQLException e) {
            throw new TransaccionExcepcion("Ocurrió un error al actualizar la persona.", e);
        }
    }

    private PersonaEntidad mapearResultado(ResultSet rs) throws SQLException {
        return new PersonaEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .numeroIdentificacion(rs.getString("numero_identificacion"))
                .primerNombre(rs.getString("primer_nombre"))
                .segundoNombre(rs.getString("segundo_nombre"))
                .primerApellido(rs.getString("primer_apellido"))
                .segundoApellido(rs.getString("segundo_apellido"))
                .numeroTelefonico(rs.getString("numero_telefonico"))
                .correoElectronico(rs.getString("correo_electronico"))
                .direccionResidencia(rs.getString("direccion_residencia"))
                .fechaNacimiento(rs.getDate("fecha_nacimiento"))
                .edad(rs.getObject("edad", Integer.class))
                .build();
    }
}
