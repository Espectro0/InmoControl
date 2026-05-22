package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ClausulaPorContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClausulaPorContratoPostgresqlDAO extends SQLDAO implements ClausulaPorContratoDAO {

  public ClausulaPorContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ClausulaPorContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT cpc.id, cpc.numeroclausula, cpc.contrato, cpc.clausula, "
            + "cc.titulo, cc.contenidolegal, cc.areareferencia, cc.tipoaplicacion, "
            + "ar.nombre as areareferencia_nombre, "
            + "ta.nombre as tipoaplicacion_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
            + "pr.tipopropiedad, pr.estrato, pr.nombreinmueble, pr.descripcioninmueble, "
            + "pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci.nombre as prop_ciudad_nombre, ci.departamento as prop_ciudad_departamento, "
            + "d.nombre as prop_departamento_nombre, d.pais as prop_departamento_pais, "
            + "ps.nombre as prop_pais_nombre "
            + "FROM clausulaporcontrato cpc "
            + "JOIN clausulacontrato cc ON cpc.clausula = cc.id "
            + "JOIN areareferencia ar ON cc.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON cc.tipoaplicacion = ta.id "
            + "JOIN contrato c ON cpc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp ON pr.tipopropiedad = tp.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci ON pr.ciudad = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "WHERE cpc.id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar la clausula por contrato por id.", e);
    }

    return null;
  }

  @Override
  public List<ClausulaPorContratoEntidad> consultarTodos() {
    String sql =
        "SELECT cpc.id, cpc.numeroclausula, cpc.contrato, cpc.clausula, "
            + "cc.titulo, cc.contenidolegal, cc.areareferencia, cc.tipoaplicacion, "
            + "ar.nombre as areareferencia_nombre, "
            + "ta.nombre as tipoaplicacion_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
            + "pr.tipopropiedad, pr.estrato, pr.nombreinmueble, pr.descripcioninmueble, "
            + "pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci.nombre as prop_ciudad_nombre, ci.departamento as prop_ciudad_departamento, "
            + "d.nombre as prop_departamento_nombre, d.pais as prop_departamento_pais, "
            + "ps.nombre as prop_pais_nombre "
            + "FROM clausulaporcontrato cpc "
            + "JOIN clausulacontrato cc ON cpc.clausula = cc.id "
            + "JOIN areareferencia ar ON cc.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON cc.tipoaplicacion = ta.id "
            + "JOIN contrato c ON cpc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp ON pr.tipopropiedad = tp.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci ON pr.ciudad = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id";
    List<ClausulaPorContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar las clausulas por contrato.", e);
    }

    return resultados;
  }

  @Override
  public List<ClausulaPorContratoEntidad> consultarPorFiltro(ClausulaPorContratoEntidad filtro) {
    String sql =
        "SELECT cpc.id, cpc.numeroclausula, cpc.contrato, cpc.clausula, "
            + "cc.titulo, cc.contenidolegal, cc.areareferencia, cc.tipoaplicacion, "
            + "ar.nombre as areareferencia_nombre, "
            + "ta.nombre as tipoaplicacion_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
            + "pr.tipopropiedad, pr.estrato, pr.nombreinmueble, pr.descripcioninmueble, "
            + "pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci.nombre as prop_ciudad_nombre, ci.departamento as prop_ciudad_departamento, "
            + "d.nombre as prop_departamento_nombre, d.pais as prop_departamento_pais, "
            + "ps.nombre as prop_pais_nombre "
            + "FROM clausulaporcontrato cpc "
            + "JOIN clausulacontrato cc ON cpc.clausula = cc.id "
            + "JOIN areareferencia ar ON cc.areareferencia = ar.id "
            + "JOIN tipoaplicacion ta ON cc.tipoaplicacion = ta.id "
            + "JOIN contrato c ON cpc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp ON pr.tipopropiedad = tp.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci ON pr.ciudad = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getNumeroClausula() != null) {
      sql += " AND cpc.numeroclausula = ?";
      parametros.add(filtro.getNumeroClausula());
    }

    if (filtro.getContrato() != null && filtro.getContrato().getId() != null) {
      sql += " AND cpc.contrato = ?";
      parametros.add(filtro.getContrato().getId());
    }

    if (filtro.getClausula() != null && filtro.getClausula().getId() != null) {
      sql += " AND cpc.clausula = ?";
      parametros.add(filtro.getClausula().getId());
    }

    List<ClausulaPorContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      for (int i = 0; i < parametros.size(); i++) {
        stmt.setObject(i + 1, parametros.get(i));
      }

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new TransaccionExcepcion(
          "Ocurrio un error al consultar clausulas por contrato por filtro.", e);
    }

    return resultados;
  }

  @Override
  public void crear(ClausulaPorContratoEntidad entidad) {
    String sql =
        "INSERT INTO clausulaporcontrato (id, numeroclausula, contrato, clausula) "
            + "VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(2, entidad.getNumeroClausula());
      stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(4, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al crear la clausula por contrato.", e);
    }
  }

  @Override
  public void actualizar(UUID id, ClausulaPorContratoEntidad entidad) {
    String sql =
        "UPDATE clausulaporcontrato SET numeroclausula = ?, contrato = ?, "
            + "clausula = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getNumeroClausula());
      stmt.setObject(2, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(3, entidad.getClausula() != null ? entidad.getClausula().getId() : null);
      stmt.setObject(4, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al actualizar la clausula por contrato.", e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM clausulaporcontrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new TransaccionExcepcion("Ocurrio un error al eliminar la clausula por contrato.", e);
    }
  }

  private ClausulaPorContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ClausulaPorContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .numeroClausula(rs.getObject("numeroclausula", Integer.class))
        .clausula(
            new ClausulaContratoEntidad.Builder()
                .id(rs.getObject("clausula", UUID.class))
                .titulo(rs.getString("titulo"))
                .contenidoLegal(rs.getString("contenidolegal"))
                .areaReferencia(
                    new AreaReferenciaEntidad.Builder()
                        .id(rs.getObject("areareferencia", UUID.class))
                        .nombre(rs.getString("areareferencia_nombre"))
                        .build())
                .tipoAplicacion(
                    new TipoAplicacionEntidad.Builder()
                        .id(rs.getObject("tipoaplicacion", UUID.class))
                        .nombre(rs.getString("tipoaplicacion_nombre"))
                        .build())
                .build())
        .contrato(
            new ContratoEntidad.Builder()
                .id(rs.getObject("contrato", UUID.class))
                .codigoContrato(rs.getString("codigocontrato"))
                .fechaInicio(rs.getDate("fechainicio"))
                .fechaFin(rs.getDate("fechafin"))
                .esActivo(rs.getObject("esactivo", Boolean.class))
                .propiedad(
                    new com.inmocontrol.entidad.PropiedadEntidad.Builder()
                        .id(rs.getObject("propiedad", UUID.class))
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
                                .id(rs.getObject("prop_ciudad", UUID.class))
                                .nombre(rs.getString("prop_ciudad_nombre"))
                                .departamento(
                                    new DepartamentoEntidad.Builder()
                                        .id(rs.getObject("prop_ciudad_departamento", UUID.class))
                                        .nombre(rs.getString("prop_departamento_nombre"))
                                        .pais(
                                            new PaisEntidad.Builder()
                                                .id(rs.getObject("prop_departamento_pais", UUID.class))
                                                .nombre(rs.getString("prop_pais_nombre"))
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build())
        .build();
  }
}
