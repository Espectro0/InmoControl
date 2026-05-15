package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.PropiedadEntidad;
import java.util.List;
import java.util.UUID;

public interface PropiedadDAO {
    PropiedadEntidad consultarPorId(UUID id);

    List<PropiedadEntidad> consultarTodos();

    PropiedadEntidad crear(PropiedadEntidad entidad);

    PropiedadEntidad actualizar(PropiedadEntidad entidad);
}
