package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.AreaReferenciaEntidad;
import java.util.List;
import java.util.UUID;

public interface AreaReferenciaDAO {
    AreaReferenciaEntidad consultarPorId(UUID id);

    List<AreaReferenciaEntidad> consultarTodos();

    AreaReferenciaEntidad crear(AreaReferenciaEntidad entidad);

    AreaReferenciaEntidad actualizar(AreaReferenciaEntidad entidad);

    void eliminar(UUID id);
}
