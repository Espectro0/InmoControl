package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import java.util.List;
import java.util.UUID;

public interface ParametroClausulaContratoDAO {
    ParametroClausulaContratoEntidad consultarPorId(UUID id);

    List<ParametroClausulaContratoEntidad> consultarTodosPorClausulaPorContrato(
            UUID clausulaPorContratoId);

    ParametroClausulaContratoEntidad crear(ParametroClausulaContratoEntidad entidad);

    ParametroClausulaContratoEntidad actualizar(ParametroClausulaContratoEntidad entidad);

    void eliminar(UUID id);
}
