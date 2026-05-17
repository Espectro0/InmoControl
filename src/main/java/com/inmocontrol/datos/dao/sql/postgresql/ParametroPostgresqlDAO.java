package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParametroDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParametroPostgresqlDAO extends SQLDAO implements ParametroDAO {

	public ParametroPostgresqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public ParametroEntidad consultarPorId(UUID id) {
		String sql = "SELECT id, placeholder, descripcion FROM parametro WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapearResultado(rs);
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar el parametro por id.", e);
		}

		return null;
	}

	@Override
	public List<ParametroEntidad> consultarTodos() {
		String sql = "SELECT id, placeholder, descripcion FROM parametro";
		List<ParametroEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar los parametros.", e);
		}

		return resultados;
	}

	@Override
	public List<ParametroEntidad> consultarPorFiltro(ParametroEntidad filtro) {
		String sql = "SELECT id, placeholder, descripcion FROM parametro WHERE 1=1";
		List<Object> parametros = new ArrayList<>();

		if (filtro.getPlaceholder() != null && !filtro.getPlaceholder().isEmpty()) {
			sql += " AND placeholder = ?";
			parametros.add(filtro.getPlaceholder());
		}

		if (filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty()) {
			sql += " AND descripcion = ?";
			parametros.add(filtro.getDescripcion());
		}

		List<ParametroEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			for (int i = 0; i < parametros.size(); i++) {
				stmt.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar parametros por filtro.", e);
		}

		return resultados;
	}

	@Override
	public void crear(ParametroEntidad entidad) {
		String sql = "INSERT INTO parametro (id, placeholder, descripcion) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, entidad.getId());
			stmt.setString(2, entidad.getPlaceholder());
			stmt.setString(3, entidad.getDescripcion());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al crear el parametro.", e);
		}
	}

	@Override
	public void actualizar(UUID id, ParametroEntidad entidad) {
		String sql = "UPDATE parametro SET placeholder = ?, descripcion = ? WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setString(1, entidad.getPlaceholder());
			stmt.setString(2, entidad.getDescripcion());
			stmt.setObject(3, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al actualizar el parametro.", e);
		}
	}

	@Override
	public void eliminar(UUID id) {
		String sql = "DELETE FROM parametro WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al eliminar el parametro.", e);
		}
	}

	private ParametroEntidad mapearResultado(ResultSet rs) throws SQLException {
		return new ParametroEntidad.Builder().id(rs.getObject("id", UUID.class))
				.placeholder(rs.getString("placeholder")).descripcion(rs.getString("descripcion")).build();
	}
}