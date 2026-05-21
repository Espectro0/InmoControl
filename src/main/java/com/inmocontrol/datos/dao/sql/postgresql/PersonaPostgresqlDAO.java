package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PersonaDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.Date;
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
        "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, "
            + "segundo_nombre, primer_apellido, segundo_apellido, numero_telefonico, "
            + "correo_electronico, direccion_residencia, ciudad_residencia_id, "
            + "fecha_nacimiento, edad FROM persona WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar la persona por id.", e);
    }

    return null;
  }

  @Override
  public List<PersonaEntidad> consultarTodos() {
    String sql =
        "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, "
            + "segundo_nombre, primer_apellido, segundo_apellido, numero_telefonico, "
            + "correo_electronico, direccion_residencia, ciudad_residencia_id, "
            + "fecha_nacimiento, edad FROM persona";
    List<PersonaEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar las personas.", e);
    }

    return resultados;
  }

  @Override
  public List<PersonaEntidad> consultarPorFiltro(PersonaEntidad filtro) {
    String sql =
        "SELECT id, tipo_documento_id, numero_identificacion, primer_nombre, "
            + "segundo_nombre, primer_apellido, segundo_apellido, numero_telefonico, "
            + "correo_electronico, direccion_residencia, ciudad_residencia_id, "
            + "fecha_nacimiento, edad FROM persona WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNumeroIdentificacion() != null && !filtro.getNumeroIdentificacion().isEmpty()) {
      sql += " AND numero_identificacion = ?";
      parametros.add(filtro.getNumeroIdentificacion());
    }

    if (filtro.getPrimerNombre() != null && !filtro.getPrimerNombre().isEmpty()) {
      sql += " AND primer_nombre = ?";
      parametros.add(filtro.getPrimerNombre());
    }

    if (filtro.getPrimerApellido() != null && !filtro.getPrimerApellido().isEmpty()) {
      sql += " AND primer_apellido = ?";
      parametros.add(filtro.getPrimerApellido());
    }

    if (filtro.getTipoDocumento() != null && filtro.getTipoDocumento().getId() != null) {
      sql += " AND tipo_documento_id = ?";
      parametros.add(filtro.getTipoDocumento().getId());
    }

    if (filtro.getCiudadResidencia() != null && filtro.getCiudadResidencia().getId() != null) {
      sql += " AND ciudad_residencia_id = ?";
      parametros.add(filtro.getCiudadResidencia().getId());
    }

    if (filtro.getCorreoElectronico() != null && !filtro.getCorreoElectronico().isEmpty()) {
      sql += " AND correo_electronico = ?";
      parametros.add(filtro.getCorreoElectronico());
    }

    List<PersonaEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar personas por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(PersonaEntidad entidad) {
    String sql =
        "INSERT INTO persona (id, tipo_documento_id, numero_identificacion, primer_nombre, "
            + "segundo_nombre, primer_apellido, segundo_apellido, numero_telefonico, "
            + "correo_electronico, direccion_residencia, ciudad_residencia_id, "
            + "fecha_nacimiento, edad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(
          2, entidad.getTipoDocumento() != null ? entidad.getTipoDocumento().getId() : null);
      stmt.setString(3, entidad.getNumeroIdentificacion());
      stmt.setString(4, entidad.getPrimerNombre());
      stmt.setString(5, entidad.getSegundoNombre());
      stmt.setString(6, entidad.getPrimerApellido());
      stmt.setString(7, entidad.getSegundoApellido());
      stmt.setString(8, entidad.getNumeroTelefonico());
      stmt.setString(9, entidad.getCorreoElectronico());
      stmt.setString(10, entidad.getDireccionResidencia());
      stmt.setObject(
          11, entidad.getCiudadResidencia() != null ? entidad.getCiudadResidencia().getId() : null);
      stmt.setDate(
          12,
          entidad.getFechaNacimiento() != null
              ? new Date(entidad.getFechaNacimiento().getTime())
              : null);
      stmt.setObject(13, entidad.getEdad());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear la persona.", e);
    }
  }

  @Override
  public void actualizar(UUID id, PersonaEntidad entidad) {
    String sql =
        "UPDATE persona SET tipo_documento_id = ?, numero_identificacion = ?, primer_nombre = ?, "
            + "segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, numero_telefonico = ?, "
            + "correo_electronico = ?, direccion_residencia = ?, ciudad_residencia_id = ?, "
            + "fecha_nacimiento = ?, edad = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(
          1, entidad.getTipoDocumento() != null ? entidad.getTipoDocumento().getId() : null);
      stmt.setString(2, entidad.getNumeroIdentificacion());
      stmt.setString(3, entidad.getPrimerNombre());
      stmt.setString(4, entidad.getSegundoNombre());
      stmt.setString(5, entidad.getPrimerApellido());
      stmt.setString(6, entidad.getSegundoApellido());
      stmt.setString(7, entidad.getNumeroTelefonico());
      stmt.setString(8, entidad.getCorreoElectronico());
      stmt.setString(9, entidad.getDireccionResidencia());
      stmt.setObject(
          10, entidad.getCiudadResidencia() != null ? entidad.getCiudadResidencia().getId() : null);
      stmt.setDate(
          11,
          entidad.getFechaNacimiento() != null
              ? new Date(entidad.getFechaNacimiento().getTime())
              : null);
      stmt.setObject(12, entidad.getEdad());
      stmt.setObject(13, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar la persona.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM persona WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar la persona.", e);
    }
  }

  private PersonaEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new PersonaEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .tipoDocumento(
            new TipoDocumentoEntidad.Builder()
                .id(rs.getObject("tipo_documento_id", UUID.class))
                .build())
        .numeroIdentificacion(rs.getString("numero_identificacion"))
        .primerNombre(rs.getString("primer_nombre"))
        .segundoNombre(rs.getString("segundo_nombre"))
        .primerApellido(rs.getString("primer_apellido"))
        .segundoApellido(rs.getString("segundo_apellido"))
        .numeroTelefonico(rs.getString("numero_telefonico"))
        .correoElectronico(rs.getString("correo_electronico"))
        .direccionResidencia(rs.getString("direccion_residencia"))
        .ciudadResidencia(
            new CiudadEntidad.Builder()
                .id(rs.getObject("ciudad_residencia_id", UUID.class))
                .build())
        .fechaNacimiento(rs.getDate("fecha_nacimiento"))
        .edad(rs.getObject("edad", Integer.class))
        .build();
  }
}
