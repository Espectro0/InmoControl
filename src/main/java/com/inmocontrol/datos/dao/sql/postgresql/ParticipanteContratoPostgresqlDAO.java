package com.inmocontrol.datos.dao.sql.postgresql;

import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.dao.sql.SQLDAO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.transversal.excepcion.TransaccionExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ParticipanteContratoPostgresqlDAO extends SQLDAO implements ParticipanteContratoDAO {

	public ParticipanteContratoPostgresqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public ParticipanteContratoEntidad consultarPorId(UUID id) {
		String sql = "SELECT id, persona_id, tipo_participante_id, contrato_id "
				+ "FROM participante_contrato WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapearResultado(rs);
			}
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al consultar el participante contrato por id.", e);
		}

		return null;
	}

	@Override
	public void crear(ParticipanteContratoEntidad entidad) {
		String sql = "INSERT INTO participante_contrato (id, persona_id, tipo_participante_id, contrato_id) "
				+ "VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, entidad.getId());
			stmt.setObject(2, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
			stmt.setObject(3, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
			stmt.setObject(4, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al crear el participante contrato.", e);
		}
	}

	@Override
	public void actualizar(UUID id, ParticipanteContratoEntidad entidad) {
		String sql = "UPDATE participante_contrato SET persona_id = ?, tipo_participante_id = ?, "
				+ "contrato_id = ? WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, entidad.getPersona() != null ? entidad.getPersona().getId() : null);
			stmt.setObject(2, entidad.getTipoParticipante() != null ? entidad.getTipoParticipante().getId() : null);
			stmt.setObject(3, entidad.getContrato() != null ? entidad.getContrato().getId() : null);
			stmt.setObject(4, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al actualizar el participante contrato.", e);
		}
	}

	@Override
	public void eliminar(UUID id) {
		String sql = "DELETE FROM participante_contrato WHERE id = ?";

		try (PreparedStatement stmt = getConexion().prepareStatement(sql)) {
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new TransaccionExcepcion("Ocurrio un error al eliminar el participante contrato.", e);
		}
	}

	private ParticipanteContratoEntidad mapearResultado(ResultSet rs) throws SQLException {
		return new ParticipanteContratoEntidad.Builder().id(rs.getObject("id", UUID.class))
				.persona(new PersonaEntidad.Builder().id(rs.getObject("persona_id", UUID.class)).build())
				.tipoParticipante(new TipoParticipanteEntidad.Builder()
						.id(rs.getObject("tipo_participante_id", UUID.class)).build())
				.contrato(new ContratoEntidad.Builder().id(rs.getObject("contrato_id", UUID.class)).build()).build();
	}
}