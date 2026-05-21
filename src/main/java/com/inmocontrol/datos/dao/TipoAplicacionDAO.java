package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import java.util.UUID;

public interface TipoAplicacionDAO
    extends ConsultarPorIdDAO<TipoAplicacionEntidad, UUID>,
        ConsultarTodosDAO<TipoAplicacionEntidad>,
        ConsultarPorFiltroDAO<TipoAplicacionEntidad> {}
