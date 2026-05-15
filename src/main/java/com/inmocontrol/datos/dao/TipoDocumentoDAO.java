package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.TipoDocumentoEntidad;
import java.util.UUID;

public interface TipoDocumentoDAO {
    TipoDocumentoEntidad consultarPorId(UUID id);

    java.util.List<TipoDocumentoEntidad> consultarTodos();
}
