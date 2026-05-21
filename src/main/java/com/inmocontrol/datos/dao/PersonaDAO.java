package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.PersonaEntidad;
import java.util.UUID;

public interface PersonaDAO
    extends ConsultarPorIdDAO<PersonaEntidad, UUID>,
        ConsultarTodosDAO<PersonaEntidad>,
        ConsultarPorFiltroDAO<PersonaEntidad>,
        CrearDAO<PersonaEntidad>,
        ActualizarDAO<PersonaEntidad, UUID>,
        EliminarDAO<UUID> {}
