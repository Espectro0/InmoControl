package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.PropiedadEntidad;
import java.util.UUID;

public interface PropiedadDAO
    extends ConsultarPorIdDAO<PropiedadEntidad, UUID>,
        ConsultarTodosDAO<PropiedadEntidad>,
        ConsultarPorFiltroDAO<PropiedadEntidad>,
        CrearDAO<PropiedadEntidad>,
        ActualizarDAO<PropiedadEntidad, UUID>,
        EliminarDAO<UUID> {}
