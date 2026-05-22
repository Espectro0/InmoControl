package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PersonaDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.transversal.UtilDate;
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
        "SELECT p.id, p.tipodocumento, p.numeroidentificacion, p.primernombre, "
            + "p.segundonombre, p.primerapellido, p.segundoapellido, p.numerotelefonico, "
            + "p.correoelectronico, p.direccionresidencia, p.ciudadresidencia, "
            + "p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre "
            + "FROM persona p "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "WHERE p.id = ?";

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
        "SELECT p.id, p.tipodocumento, p.numeroidentificacion, p.primernombre, "
            + "p.segundonombre, p.primerapellido, p.segundoapellido, p.numerotelefonico, "
            + "p.correoelectronico, p.direccionresidencia, p.ciudadresidencia, "
            + "p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre "
            + "FROM persona p "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id";
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
        "SELECT p.id, p.tipodocumento, p.numeroidentificacion, p.primernombre, "
            + "p.segundonombre, p.primerapellido, p.segundoapellido, p.numerotelefonico, "
            + "p.correoelectronico, p.direccionresidencia, p.ciudadresidencia, "
            + "p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre "
            + "FROM persona p "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNumeroIdentificacion() != null && !filtro.getNumeroIdentificacion().isEmpty()) {
      sql += " AND p.numeroidentificacion = ?";
      parametros.add(filtro.getNumeroIdentificacion());
    }

    if (filtro.getPrimerNombre() != null && !filtro.getPrimerNombre().isEmpty()) {
      sql += " AND p.primernombre = ?";
      parametros.add(filtro.getPrimerNombre());
    }

    if (filtro.getPrimerApellido() != null && !filtro.getPrimerApellido().isEmpty()) {
      sql += " AND p.primerapellido = ?";
      parametros.add(filtro.getPrimerApellido());
    }

    if (filtro.getTipoDocumento() != null && filtro.getTipoDocumento().getId() != null) {
      sql += " AND p.tipodocumento = ?";
      parametros.add(filtro.getTipoDocumento().getId());
    }

    if (filtro.getCiudadResidencia() != null && filtro.getCiudadResidencia().getId() != null) {
      sql += " AND p.ciudadresidencia = ?";
      parametros.add(filtro.getCiudadResidencia().getId());
    }

    if (filtro.getCorreoElectronico() != null && !filtro.getCorreoElectronico().isEmpty()) {
      sql += " AND p.correoelectronico = ?";
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
        "INSERT INTO persona (id, tipodocumento, numeroidentificacion, primernombre, "
            + "segundonombre, primerapellido, segundoapellido, numerotelefonico, "
            + "correoelectronico, direccionresidencia, ciudadresidencia, "
            + "fechanacimiento, edad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        "UPDATE persona SET tipodocumento = ?, numeroidentificacion = ?, primernombre = ?, "
            + "segundonombre = ?, primerapellido = ?, segundoapellido = ?, numerotelefonico = ?, "
            + "correoelectronico = ?, direccionresidencia = ?, ciudadresidencia = ?, "
            + "fechanacimiento = ?, edad = ? WHERE id = ?";

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
                .id(rs.getObject("tipodocumento", UUID.class))
                .nombre(rs.getString("tipodocumento_nombre"))
                .build())
        .numeroIdentificacion(rs.getString("numeroidentificacion"))
        .primerNombre(rs.getString("primernombre"))
        .segundoNombre(rs.getString("segundonombre"))
        .primerApellido(rs.getString("primerapellido"))
        .segundoApellido(rs.getString("segundoapellido"))
        .numeroTelefonico(rs.getString("numerotelefonico"))
        .correoElectronico(rs.getString("correoelectronico"))
        .direccionResidencia(rs.getString("direccionresidencia"))
        .ciudadResidencia(
            new CiudadEntidad.Builder()
                .id(rs.getObject("ciudadresidencia", UUID.class))
                .nombre(rs.getString("ciudad_nombre"))
                .departamento(
                    new DepartamentoEntidad.Builder()
                        .id(rs.getObject("departamento", UUID.class))
                        .nombre(rs.getString("departamento_nombre"))
                        .pais(
                            new PaisEntidad.Builder()
                                .id(rs.getObject("pais", UUID.class))
                                .nombre(rs.getString("pais_nombre"))
                                .build())
                        .build())
                .build())
        .fechaNacimiento(rs.getDate("fechanacimiento"))
        .edad(UtilDate.calcularEdad(rs.getDate("fechanacimiento")))
        .build();
  }
}
