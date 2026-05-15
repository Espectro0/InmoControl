package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.CiudadEntidad;
import java.util.List;
import java.util.UUID;

public interface CiudadDAO {
    CiudadEntidad consultarPorId(UUID id);

    List<CiudadEntidad> consultarTodos();

    List<CiudadEntidad> consultarTodosPorDepartamento(UUID departamentoId);
}
