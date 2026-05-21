package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.CiudadEntidad;
import java.util.UUID;

public interface CiudadDAO
    extends ConsultarPorIdDAO<CiudadEntidad, UUID>,
        ConsultarTodosDAO<CiudadEntidad>,
        ConsultarPorFiltroDAO<CiudadEntidad> {}
