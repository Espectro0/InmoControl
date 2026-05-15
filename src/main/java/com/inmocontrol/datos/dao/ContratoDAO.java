package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ContratoEntidad;
import java.util.List;
import java.util.UUID;

public interface ContratoDAO {
    ContratoEntidad consultarPorId(UUID id);

    List<ContratoEntidad> consultarTodos();

    ContratoEntidad crear(ContratoEntidad entidad);

    ContratoEntidad actualizar(ContratoEntidad entidad);

    void actualizarEstado(UUID id, boolean activo);
}
