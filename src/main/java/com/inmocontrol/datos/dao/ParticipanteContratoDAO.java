package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import java.util.UUID;

public interface ParticipanteContratoDAO
		extends ConsultarPorIdDAO<ParticipanteContratoEntidad, UUID>, CrearDAO<ParticipanteContratoEntidad>,
				ActualizarDAO<ParticipanteContratoEntidad, UUID>, EliminarDAO<UUID> {
}