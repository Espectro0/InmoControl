package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.TipoParticipanteDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoParticipantePostgresqlDAO extends SQLDAO implements TipoParticipanteDAO {

	public TipoParticipantePostgresqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public TipoParticipanteEntidad consultarPorId(UUID id) {
		String sql = "SELECT id, nombre FROM tipo_participante WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapearResultado(rs);
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar el tipo participante por id.", e);
		}

		return null;
	}

	@Override
	public List<TipoParticipanteEntidad> consultarTodos() {
		String sql = "SELECT id, nombre FROM tipo_participante";
		List<TipoParticipanteEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar los tipos participante.", e);
		}

		return resultados;
	}

	@Override
	public List<TipoParticipanteEntidad> consultarPorFiltro(TipoParticipanteEntidad filtro) {
		String sql = "SELECT id, nombre FROM tipo_participante WHERE 1=1";
		List<Object> parametros = new ArrayList<>();

		if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
			sql += " AND nombre = ?";
			parametros.add(filtro.getNombre());
		}

		List<TipoParticipanteEntidad> resultados = new ArrayList<>();

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			for (int i = 0; i < parametros.size(); i++) {
				stmt.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(mapearResultado(rs));
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar tipos participante por filtro.", e);
		}

		return resultados;
	}

	@Override
	public void crear(TipoParticipanteEntidad entidad) {
		String sql = "INSERT INTO tipo_participante (id, nombre) VALUES (?, ?)";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, entidad.getId());
			stmt.setString(2, entidad.getNombre());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al crear el tipo participante.", e);
		}
	}

	@Override
	public void actualizar(UUID id, TipoParticipanteEntidad entidad) {
		String sql = "UPDATE tipo_participante SET nombre = ? WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setString(1, entidad.getNombre());
			stmt.setObject(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al actualizar el tipo participante.", e);
		}
	}

	private TipoParticipanteEntidad mapearResultado(ResultSet rs) throws SQLException {
		return new TipoParticipanteEntidad.Builder().id(rs.getObject("id", UUID.class)).nombre(rs.getString("nombre"))
				.build();
	}
}