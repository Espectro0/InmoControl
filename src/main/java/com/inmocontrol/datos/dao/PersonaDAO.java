package com.inmocontrol.datos.dao;

import com.inmocontrol.entidad.PersonaEntidad;
import java.util.List;
import java.util.UUID;

public interface PersonaDAO {
    PersonaEntidad consultarPorId(UUID id);

    List<PersonaEntidad> consultarTodos();

    PersonaEntidad consultarPorNumeroIdentificacion(String numero);

    PersonaEntidad crear(PersonaEntidad entidad);

    PersonaEntidad actualizar(PersonaEntidad entidad);
}
