package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratoPostgresqlDAO extends SQLDAO implements ContratoDAO {

	public ContratoPostgresqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public ContratoEntidad consultarPorId(UUID id) {
		String sql = "SELECT c.id, c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
				+ "p.id as prop_id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
				+ "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, " + "tp.nombre as tipopropiedad_nombre, "
				+ "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
				+ "ci.nombre as ciudad_nombre, ci.departamento, " + "d.nombre as departamento_nombre, d.pais, "
				+ "ps.nombre as pais_nombre " + "FROM contrato c " + "JOIN propiedad p ON c.propiedad = p.id "
				+ "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id " + "JOIN estrato e ON p.estrato = e.id "
				+ "JOIN ciudad ci ON p.ciudad = ci.id " + "JOIN departamento d ON ci.departamento = d.id "
				+ "JOIN pais ps ON d.pais = ps.id " + "WHERE c.id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapearResultado(rs);
			}
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.consultarPorId() - " + e.getMessage(), e);
		}

		return null;
	}

	@Override
	public List<ContratoEntidad> consultarTodos() {
		String sql = "SELECT c.id, c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
				+ "p.id as prop_id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
				+ "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, " + "tp.nombre as tipopropiedad_nombre, "
				+ "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
				+ "ci.nombre as ciudad_nombre, ci.departamento, " + "d.nombre as departamento_nombre, d.pais, "
				+ "ps.nombre as pais_nombre " + "FROM contrato c " + "JOIN propiedad p ON c.propiedad = p.id "
				+ "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id " + "JOIN estrato e ON p.estrato = e.id "
				+ "JOIN ciudad ci ON p.ciudad = ci.id " + "JOIN departamento d ON ci.departamento = d.id "
				+ "JOIN pais ps ON d.pais = ps.id";
		List<ContratoEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.consultarTodos() - " + e.getMessage(), e);
		}

		return resultados;
	}

	@Override
	public List<ContratoEntidad> consultarPorFiltro(ContratoEntidad filtro) {
		String sql = "SELECT c.id, c.codigocontrato, c.fechainicio, c.fechafin, c.esactivo, c.propiedad, "
				+ "p.id as prop_id, p.tipopropiedad, p.estrato, p.nombreinmueble, "
				+ "p.descripcioninmueble, p.areametros, p.direccion, p.ciudad, " + "tp.nombre as tipopropiedad_nombre, "
				+ "e.nombre as estrato_nombre, e.descripcion as estrato_descripcion, "
				+ "ci.nombre as ciudad_nombre, ci.departamento, " + "d.nombre as departamento_nombre, d.pais, "
				+ "ps.nombre as pais_nombre " + "FROM contrato c " + "JOIN propiedad p ON c.propiedad = p.id "
				+ "JOIN tipopropiedad tp ON p.tipopropiedad = tp.id " + "JOIN estrato e ON p.estrato = e.id "
				+ "JOIN ciudad ci ON p.ciudad = ci.id " + "JOIN departamento d ON ci.departamento = d.id "
				+ "JOIN pais ps ON d.pais = ps.id " + "WHERE 1=1";
		List<Object> parametros = new ArrayList<>();

		if (filtro.getCodigoContrato() != null && !filtro.getCodigoContrato().isEmpty()) {
			sql += " AND c.codigocontrato = ?";
			parametros.add(filtro.getCodigoContrato());
		}

		if (filtro.getEsActivo() != null) {
			sql += " AND c.esactivo = ?";
			parametros.add(filtro.getEsActivo());
		}

		if (filtro.getPropiedad() != null && filtro.getPropiedad().getId() != null
				&& !UtilUUID.UUID_CERO.equals(filtro.getPropiedad().getId())) {
			sql += " AND c.propiedad = ?";
			parametros.add(filtro.getPropiedad().getId());
		}

		if (filtro.getFechaInicio() != null) {
			sql += " AND c.fechainicio >= ?";
			parametros.add(new Date(filtro.getFechaInicio().getTime()));
		}

		if (filtro.getFechaFin() != null) {
			sql += " AND c.fechafin <= ?";
			parametros.add(new Date(filtro.getFechaFin().getTime()));
		}

		List<ContratoEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			for (int i = 0; i < parametros.size(); i++) {
				stmt.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.consultarPorFiltro() - " + e.getMessage(), e);
		}

		return resultados;
	}

	@Override
	public void crear(ContratoEntidad entidad) {
		String sql = "INSERT INTO contrato (id, codigocontrato, fechainicio, fechafin, esactivo, propiedad) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, entidad.getId());
			stmt.setString(2, entidad.getCodigoContrato());
			stmt.setDate(3, entidad.getFechaInicio() != null ? new Date(entidad.getFechaInicio().getTime()) : null);
			stmt.setDate(4, entidad.getFechaFin() != null ? new Date(entidad.getFechaFin().getTime()) : null);
			stmt.setObject(5, entidad.getEsActivo());
			stmt.setObject(6, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.crear() - " + e.getMessage(), e);
		}
	}

	@Override
	public void actualizar(UUID id, ContratoEntidad entidad) {
		String sql = "UPDATE contrato SET codigocontrato = ?, fechainicio = ?, fechafin = ?, "
				+ "esactivo = ?, propiedad = ? WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setString(1, entidad.getCodigoContrato());
			stmt.setDate(2, entidad.getFechaInicio() != null ? new Date(entidad.getFechaInicio().getTime()) : null);
			stmt.setDate(3, entidad.getFechaFin() != null ? new Date(entidad.getFechaFin().getTime()) : null);
			stmt.setObject(4, entidad.getEsActivo());
			stmt.setObject(5, entidad.getPropiedad() != null ? entidad.getPropiedad().getId() : null);
			stmt.setObject(6, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.actualizar() - " + e.getMessage(), e);
		}
	}

	@Override
	public void eliminar(UUID id) {
		String sql = "DELETE FROM contrato WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ContratoPostgresqlDAO.eliminar() - " + e.getMessage(), e);
		}
	}

	private ContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
		return new ContratoEntidad.Builder().id(rs.getObject("id", UUID.class))
				.codigoContrato(rs.getString("codigocontrato")).fechaInicio(rs.getDate("fechainicio"))
				.fechaFin(rs.getDate("fechafin")).esActivo(rs.getObject("esactivo", Boolean.class))
				.propiedad(new PropiedadEntidad.Builder().id(rs.getObject("prop_id", UUID.class))
						.tipoPropiedad(new TipoPropiedadEntidad.Builder().id(rs.getObject("tipopropiedad", UUID.class))
								.nombre(rs.getString("tipopropiedad_nombre")).build())
						.estrato(new EstratoEntidad.Builder().id(rs.getObject("estrato", UUID.class))
								.nombre(rs.getString("estrato_nombre")).descripcion(rs.getString("estrato_descripcion"))
								.build())
						.nombreInmueble(rs.getString("nombreinmueble"))
						.descripcionInmueble(rs.getString("descripcioninmueble"))
						.areaMetros(rs.getObject("areametros", Integer.class)).direccion(rs.getString("direccion"))
						.ciudad(new CiudadEntidad.Builder().id(rs.getObject("ciudad", UUID.class))
								.nombre(rs.getString("ciudad_nombre"))
								.departamento(
										new DepartamentoEntidad.Builder().id(rs.getObject("departamento", UUID.class))
												.nombre(rs.getString("departamento_nombre"))
												.pais(new PaisEntidad.Builder().id(rs.getObject("pais", UUID.class))
														.nombre(rs.getString("pais_nombre")).build())
												.build())
								.build())
						.build())
				.build();
	}
}
