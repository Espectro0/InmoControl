package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.TipoPropiedadEntidad;
import java.util.UUID;

public interface TipoPropiedadDAO {
    TipoPropiedadEntidad consultarPorId(UUID id);

    java.util.List<TipoPropiedadEntidad> consultarTodos();
}
