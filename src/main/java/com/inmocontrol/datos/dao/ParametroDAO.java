package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ParametroEntidad;
import java.util.UUID;

public interface ParametroDAO {
    ParametroEntidad consultarPorId(UUID id);

    java.util.List<ParametroEntidad> consultarTodos();
}
