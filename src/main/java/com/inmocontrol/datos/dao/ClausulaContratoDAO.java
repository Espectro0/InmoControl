package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ClausulaContratoEntidad;
import java.util.List;
import java.util.UUID;

public interface ClausulaContratoDAO {
    ClausulaContratoEntidad consultarPorId(UUID id);

    List<ClausulaContratoEntidad> consultarTodos();

    ClausulaContratoEntidad crear(ClausulaContratoEntidad entidad);

    ClausulaContratoEntidad actualizar(ClausulaContratoEntidad entidad);

    void eliminar(UUID id);
}
