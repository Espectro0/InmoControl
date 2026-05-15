package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.TipoAplicacionEntidad;
import java.util.UUID;

public interface TipoAplicacionDAO {
    TipoAplicacionEntidad consultarPorId(UUID id);

    java.util.List<TipoAplicacionEntidad> consultarTodos();
}
