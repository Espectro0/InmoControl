package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import java.util.UUID;

public interface TipoParticipanteDAO
    extends ConsultarPorIdDAO<TipoParticipanteEntidad, UUID>,
        ConsultarTodosDAO<TipoParticipanteEntidad>,
        ConsultarPorFiltroDAO<TipoParticipanteEntidad>,
        CrearDAO<TipoParticipanteEntidad>,
        ActualizarDAO<TipoParticipanteEntidad, UUID>,
        EliminarDAO<UUID> {}
