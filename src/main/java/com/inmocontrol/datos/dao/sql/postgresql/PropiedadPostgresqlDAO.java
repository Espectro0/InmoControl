package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PropiedadDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

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
        "SELECT p.id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
            + "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, "
            + "c.nombre as ciudad_nombre, c.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "tp.nombre as tipopropiedad_nombre "
            + "FROM propiedad p "
            + "JOIN ciudad c ON p.ciudad = c.id "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN estrato e ON p.estrato = e.id "
            + "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id "
            + "WHERE p.id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.consultarPorId() - " + e.getMessage(),
          e);
    }

    return null;
  }

  @Override
  public List<PropiedadEntidad> consultarTodos() {
    String sql =
        "SELECT p.id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
            + "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, "
            + "c.nombre as ciudad_nombre, c.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "tp.nombre as tipopropiedad_nombre "
            + "FROM propiedad p "
            + "JOIN ciudad c ON p.ciudad = c.id "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN estrato e ON p.estrato = e.id "
            + "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id";
    List<PropiedadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.consultarTodos() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public List<PropiedadEntidad> consultarPorFiltro(PropiedadEntidad filtro) {
    String sql =
        "SELECT p.id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
            + "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, "
            + "c.nombre as ciudad_nombre, c.departamento, "
            + "d.nombre as departamento_nombre, d.pais, "
            + "ps.nombre as pais_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "tp.nombre as tipopropiedad_nombre "
            + "FROM propiedad p "
            + "JOIN ciudad c ON p.ciudad = c.id "
            + "JOIN departamento d ON c.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN estrato e ON p.estrato = e.id "
            + "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombreInmueble() != null && !filtro.getNombreInmueble().isEmpty()) {
      sql += " AND p.nombreinmueble = ?";
      parametros.add(filtro.getNombreInmueble());
    }

    if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
      sql += " AND p.direccion = ?";
      parametros.add(filtro.getDireccion());
    }

    List<PropiedadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public void crear(PropiedadEntidad entidad) {
    String sql =
        "INSERT INTO propiedad (id, tipopropiedad, estrato, nombreinmueble, "
            + "descripcioninmueble, areametros, direccion, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(
          2, entidad.getTipoPropiedad() != null ? entidad.getTipoPropiedad().getId() : null);
      stmt.setObject(3, entidad.getEstrato() != null ? entidad.getEstrato().getId() : null);
      stmt.setString(4, entidad.getNombreInmueble());
      stmt.setString(5, entidad.getDescripcionInmueble());
      stmt.setObject(6, entidad.getAreaMetros());
      stmt.setString(7, entidad.getDireccion());
      stmt.setObject(8, entidad.getCiudad() != null ? entidad.getCiudad().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.crear() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void actualizar(UUID id, PropiedadEntidad entidad) {
    String sql =
        "UPDATE propiedad SET tipopropiedad = ?, estrato = ?, nombreinmueble = ?, "
            + "descripcioninmueble = ?, areametros = ?, direccion = ?, ciudad = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(
          1, entidad.getTipoPropiedad() != null ? entidad.getTipoPropiedad().getId() : null);
      stmt.setObject(2, entidad.getEstrato() != null ? entidad.getEstrato().getId() : null);
      stmt.setString(3, entidad.getNombreInmueble());
      stmt.setString(4, entidad.getDescripcionInmueble());
      stmt.setObject(5, entidad.getAreaMetros());
      stmt.setString(6, entidad.getDireccion());
      stmt.setObject(7, entidad.getCiudad() != null ? entidad.getCiudad().getId() : null);
      stmt.setObject(8, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.actualizar() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM propiedad WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en PropiedadPostgresqlDAO.eliminar() - " + e.getMessage(),
          e);
    }
  }

  private PropiedadEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new PropiedadEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .tipoPropiedad(
            new TipoPropiedadEntidad.Builder()
                .id(rs.getObject("tipopropiedad", UUID.class))
                .nombre(rs.getString("tipopropiedad_nombre"))
                .build())
        .estrato(
            new EstratoEntidad.Builder()
                .id(rs.getObject("estrato", UUID.class))
                .nombre(rs.getString("estrato_nombre"))
                .descripcion(rs.getString("estrato_descripcion"))
                .build())
        .nombreInmueble(rs.getString("nombreinmueble"))
        .descripcionInmueble(rs.getString("descripcioninmueble"))
        .areaMetros(rs.getObject("areametros", Integer.class))
        .direccion(rs.getString("direccion"))
        .ciudad(
            new CiudadEntidad.Builder()
                .id(rs.getObject("ciudad", UUID.class))
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
        .build();
  }
}

