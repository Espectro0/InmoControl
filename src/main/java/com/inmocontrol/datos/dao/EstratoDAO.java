package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.EstratoEntidad;
import java.util.UUID;

public interface EstratoDAO {
    EstratoEntidad consultarPorId(UUID id);

    java.util.List<EstratoEntidad> consultarTodos();
}
