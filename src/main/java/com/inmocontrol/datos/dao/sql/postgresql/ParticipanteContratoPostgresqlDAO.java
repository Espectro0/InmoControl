package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.UtilDate;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParticipanteContratoPostgresqlDAO extends SQLDAO implements ParticipanteContratoDAO {

  public ParticipanteContratoPostgresqlDAO(Connection conexion) {
    super(conexion);
  }

  @Override
  public ParticipanteContratoEntidad consultarPorId(UUID id) {
    String sql =
        "SELECT pc.id, pc.persona, pc.tipoparticipante, pc.contrato, "
            + "tp.nombre as tipoparticipante_nombre, "
            + "p.tipodocumento, p.numeroidentificacion, p.primernombre, p.segundonombre, "
            + "p.primerapellido, p.segundoapellido, p.numerotelefonico, p.correoelectronico, "
            + "p.direccionresidencia, p.ciudadresidencia, p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento as ciudad_departamento, "
            + "d.nombre as departamento_nombre, d.pais as departamento_pais, "
            + "ps.nombre as pais_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, "
            + "pr.id as prop_id, pr.tipopropiedad, pr.estrato, pr.nombreinmueble, "
            + "pr.descripcioninmueble, pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp2.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci2.nombre as prop_ciudad_nombre, ci2.departamento as prop_ciudad_departamento, "
            + "d2.nombre as prop_departamento_nombre, d2.pais as prop_departamento_pais, "
            + "ps2.nombre as prop_pais_nombre "
            + "FROM participantecontrato pc "
            + "JOIN tipoparticipante tp ON pc.tipoparticipante = tp.id "
            + "JOIN persona p ON pc.persona = p.id "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN contrato c ON pc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp2 ON pr.tipopropiedad = tp2.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci2 ON pr.ciudad = ci2.id "
            + "JOIN departamento d2 ON ci2.departamento = d2.id "
            + "JOIN pais ps2 ON d2.pais = ps2.id "
            + "WHERE pc.id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return mapearResultado(rs);
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ParticipanteContratoPostgresqlDAO.consultarPorId() - " + e.getMessage(),
          e);
    }

    return null;
  }

  @Override
  public void crear(ParticipanteContratoEntidad entidad) {
    String sql =
        "INSERT INTO participantecontrato (id, persona, tipoparticipante, contrato) "
            + "VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getId());
      stmt.setObject(2, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
      stmt.setObject(
          3, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
      stmt.setObject(4, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ParticipanteContratoPostgresqlDAO.crear() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void actualizar(UUID id, ParticipanteContratoEntidad entidad) {
    String sql =
        "UPDATE participantecontrato SET persona = ?, tipoparticipante = ?, "
            + "contrato = ? WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
      stmt.setObject(
          2, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
      stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
      stmt.setObject(4, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ParticipanteContratoPostgresqlDAO.actualizar() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public void eliminar(UUID id) {
    String sql = "DELETE FROM participantecontrato WHERE id = ?";

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      stmt.setObject(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ParticipanteContratoPostgresqlDAO.eliminar() - " + e.getMessage(),
          e);
    }
  }

  @Override
  public List<ParticipanteContratoEntidad> consultarTodos() {
    String sql =
        "SELECT pc.id, pc.persona, pc.tipoparticipante, pc.contrato, "
            + "tp.nombre as tipoparticipante_nombre, "
            + "p.tipodocumento, p.numeroidentificacion, p.primernombre, p.segundonombre, "
            + "p.primerapellido, p.segundoapellido, p.numerotelefonico, p.correoelectronico, "
            + "p.direccionresidencia, p.ciudadresidencia, p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento as ciudad_departamento, "
            + "d.nombre as departamento_nombre, d.pais as departamento_pais, "
            + "ps.nombre as pais_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, "
            + "pr.id as prop_id, pr.tipopropiedad, pr.estrato, pr.nombreinmueble, "
            + "pr.descripcioninmueble, pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp2.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci2.nombre as prop_ciudad_nombre, ci2.departamento as prop_ciudad_departamento, "
            + "d2.nombre as prop_departamento_nombre, d2.pais as prop_departamento_pais, "
            + "ps2.nombre as prop_pais_nombre "
            + "FROM participantecontrato pc "
            + "JOIN tipoparticipante tp ON pc.tipoparticipante = tp.id "
            + "JOIN persona p ON pc.persona = p.id "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN contrato c ON pc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp2 ON pr.tipopropiedad = tp2.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci2 ON pr.ciudad = ci2.id "
            + "JOIN departamento d2 ON ci2.departamento = d2.id "
            + "JOIN pais ps2 ON d2.pais = ps2.id";
    List<ParticipanteContratoEntidad> resultados = new ArrayList<>();

    try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        resultados.add(mapearResultado(rs));
      }
    } catch (SQLException e) {
      throw new InmocontrolExcepcion(
          "No se pudo completar la operacion. Intente mas tarde.",
          "Error en ParticipanteContratoPostgresqlDAO.consultarTodos() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  @Override
  public List<ParticipanteContratoEntidad> consultarPorFiltro(ParticipanteContratoEntidad filtro) {
    String sql =
        "SELECT pc.id, pc.persona, pc.tipoparticipante, pc.contrato, "
            + "tp.nombre as tipoparticipante_nombre, "
            + "p.tipodocumento, p.numeroidentificacion, p.primernombre, p.segundonombre, "
            + "p.primerapellido, p.segundoapellido, p.numerotelefonico, p.correoelectronico, "
            + "p.direccionresidencia, p.ciudadresidencia, p.fechanacimiento, "
            + "td.nombre as tipodocumento_nombre, "
            + "ci.nombre as ciudad_nombre, ci.departamento as ciudad_departamento, "
            + "d.nombre as departamento_nombre, d.pais as departamento_pais, "
            + "ps.nombre as pais_nombre, "
            + "c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, "
            + "pr.id as prop_id, pr.tipopropiedad, pr.estrato, pr.nombreinmueble, "
            + "pr.descripcioninmueble, pr.areametros, pr.direccion, pr.ciudad as prop_ciudad, "
            + "tp2.nombre as tipopropiedad_nombre, "
            + "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
            + "ci2.nombre as prop_ciudad_nombre, ci2.departamento as prop_ciudad_departamento, "
            + "d2.nombre as prop_departamento_nombre, d2.pais as prop_departamento_pais, "
            + "ps2.nombre as prop_pais_nombre "
            + "FROM participantecontrato pc "
            + "JOIN tipoparticipante tp ON pc.tipoparticipante = tp.id "
            + "JOIN persona p ON pc.persona = p.id "
            + "JOIN tipodocumento td ON p.tipodocumento = td.id "
            + "JOIN ciudad ci ON p.ciudadresidencia = ci.id "
            + "JOIN departamento d ON ci.departamento = d.id "
            + "JOIN pais ps ON d.pais = ps.id "
            + "JOIN contrato c ON pc.contrato = c.id "
            + "JOIN propiedad pr ON c.propiedad = pr.id "
            + "JOIN tipopropiedad tp2 ON pr.tipopropiedad = tp2.id "
            + "JOIN estrato e ON pr.estrato = e.id "
            + "JOIN ciudad ci2 ON pr.ciudad = ci2.id "
            + "JOIN departamento d2 ON ci2.departamento = d2.id "
            + "JOIN pais ps2 ON d2.pais = ps2.id "
            + "WHERE 1=1";
    List<Object> parametros = new ArrayList<>();

    if (filtro.getPersona() != null && filtro.getPersona().getId() != null) {
      sql += " AND pc.persona = ?";
      parametros.add(filtro.getPersona().getId());
    }

    if (filtro.getTipoParticipante() != null && filtro.getTipoParticipante().getId() != null) {
      sql += " AND pc.tipoparticipante = ?";
      parametros.add(filtro.getTipoParticipante().getId());
    }

    if (filtro.getContrato() != null && filtro.getContrato().getId() != null) {
      sql += " AND pc.contrato = ?";
      parametros.add(filtro.getContrato().getId());
    }

    List<ParticipanteContratoEntidad> resultados = new ArrayList<>();

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
          "Error en ParticipanteContratoPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(),
          e);
    }

    return resultados;
  }

  private ParticipanteContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
    return new ParticipanteContratoEntidad.Builder()
        .id(rs.getObject("id", UUID.class))
        .tipoParticipante(
            new TipoParticipanteEntidad.Builder()
                .id(rs.getObject("tipoparticipante", UUID.class))
                .nombre(rs.getString("tipoparticipante_nombre"))
                .build())
        .persona(
            new PersonaEntidad.Builder()
                .id(rs.getObject("persona", UUID.class))
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
                                .id(rs.getObject("ciudad_departamento", UUID.class))
                                .nombre(rs.getString("departamento_nombre"))
                                .pais(
                                    new PaisEntidad.Builder()
                                        .id(rs.getObject("departamento_pais", UUID.class))
                                        .nombre(rs.getString("pais_nombre"))
                                        .build())
                                .build())
                        .build())
                .fechaNacimiento(rs.getDate("fechanacimiento"))
                .edad(UtilDate.calcularEdad(rs.getDate("fechanacimiento")))
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
                        .id(rs.getObject("prop_id", UUID.class))
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

