package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.TipoParticipanteEntidad;
import java.util.UUID;

public interface TipoParticipanteDAO {
    TipoParticipanteEntidad consultarPorId(UUID id);

    java.util.List<TipoParticipanteEntidad> consultarTodos();
}
