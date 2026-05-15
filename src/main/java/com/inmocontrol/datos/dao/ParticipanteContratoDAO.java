package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import java.util.List;
import java.util.UUID;

public interface ParticipanteContratoDAO {
    ParticipanteContratoEntidad consultarPorId(UUID id);

    List<ParticipanteContratoEntidad> consultarTodosPorContrato(UUID contratoId);

    ParticipanteContratoEntidad crear(ParticipanteContratoEntidad entidad);

    void eliminar(UUID id);
}
