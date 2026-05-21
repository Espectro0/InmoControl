package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import java.util.UUID;

public interface TipoPropiedadDAO
    extends ConsultarPorIdDAO<TipoPropiedadEntidad, UUID>,
        ConsultarTodosDAO<TipoPropiedadEntidad>,
        ConsultarPorFiltroDAO<TipoPropiedadEntidad> {}
