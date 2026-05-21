package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.PropiedadDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
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
        "SELECT id, tipo_propiedad_id, estrato_id, nombre_inmueble, "
            + "descripcion_inmueble, area_metros, direccion, ciudad_id FROM propiedad WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar la propiedad por id.", e);
    }

    return null;
  }

  @Override
  public List<PropiedadEntidad> consultarTodos() {
    String sql =
        "SELECT id, tipo_propiedad_id, estrato_id, nombre_inmueble, "
            + "descripcion_inmueble, area_metros, direccion, ciudad_id FROM propiedad";
    List<PropiedadEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al consultar las propiedades.", e);
    }

    return resultados;
  }

  @Override
  public List<PropiedadEntidad> consultarPorFiltro(PropiedadEntidad filtro) {
    String sql =
        "SELECT id, tipo_propiedad_id, estrato_id, nombre_inmueble, "
            + "descripcion_inmueble, area_metros, direccion, ciudad_id FROM propiedad WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNombreInmueble() != null && !filtro.getNombreInmueble().isEmpty()) {
      sql += " AND nombre_inmueble = ?";
      parametros.add(filtro.getNombreInmueble());
    }

    if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
      sql += " AND direccion = ?";
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
      throw new TransaccionExcepcion("Ocurrio un error al consultar propiedades por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(PropiedadEntidad entidad) {
    String sql =
        "INSERT INTO propiedad (id, tipo_propiedad_id, estrato_id, nombre_inmueble, "
            + "descripcion_inmueble, area_metros, direccion, ciudad_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
      throw new TransaccionExcepcion("Ocurrio un error al crear la propiedad.", e);
    }
  }

  @Override
  public void actualizar(UUID id, PropiedadEntidad entidad) {
    String sql =
        "UPDATE propiedad SET tipo_propiedad_id = ?, estrato_id = ?, nombre_inmueble = ?, "
            + "descripcion_inmueble = ?, area_metros = ?, direccion = ?, ciudad_id = ? WHERE id = ?";

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
      throw new TransaccionExcepcion("Ocurrio un error al actualizar la propiedad.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM propiedad WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar la propiedad.", e);
    }
  }

  private PropiedadEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new PropiedadEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .tipoPropiedad(
            new TipoPropiedadEntidad.Builder()
                .id(rs.getObject("tipo_propiedad_id", UUID.class))
                .build())
        .estrato(new EstratoEntidad.Builder().id(rs.getObject("estrato_id", UUID.class)).build())
        .nombreInmueble(rs.getString("nombre_inmueble"))
        .descripcionInmueble(rs.getString("descripcion_inmueble"))
        .areaMetros(rs.getObject("area_metros", Integer.class))
        .direccion(rs.getString("direccion"))
        .ciudad(new CiudadEntidad.Builder().id(rs.getObject("ciudad_id", UUID.class)).build())
        .build();
  }
}
