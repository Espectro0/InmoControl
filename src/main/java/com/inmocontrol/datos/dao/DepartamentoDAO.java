package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.DepartamentoEntidad;
import java.util.List;
import java.util.UUID;

public interface DepartamentoDAO {
    DepartamentoEntidad consultarPorId(UUID id);

    List<DepartamentoEntidad> consultarTodos();

    List<DepartamentoEntidad> consultarTodosPorPais(UUID paisId);
}
