package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ParametroEntidad;
import java.util.UUID;

public interface ParametroDAO
    extends ConsultarPorIdDAO<ParametroEntidad, UUID>,
        ConsultarTodosDAO<ParametroEntidad>,
        ConsultarPorFiltroDAO<ParametroEntidad>,
        CrearDAO<ParametroEntidad>,
        ActualizarDAO<ParametroEntidad, UUID>,
        EliminarDAO<UUID> {}
