package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.PaisEntidad;
import java.util.UUID;

public interface PaisDAO {
    PaisEntidad consultarPorId(UUID id);

    java.util.List<PaisEntidad> consultarTodos();
}
