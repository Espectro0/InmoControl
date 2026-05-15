package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import java.util.List;
import java.util.UUID;

public interface ClausulaPorContratoDAO {
    ClausulaPorContratoEntidad consultarPorId(UUID id);

    List<ClausulaPorContratoEntidad> consultarTodosPorContrato(UUID contratoId);

    ClausulaPorContratoEntidad crear(ClausulaPorContratoEntidad entidad);

    ClausulaPorContratoEntidad actualizar(ClausulaPorContratoEntidad entidad);

    void eliminar(UUID id);
}
