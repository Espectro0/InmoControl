package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.PaisEntidad;
import java.util.UUID;

public interface PaisDAO extends ConsultarPorIdDAO<PaisEntidad, UUID>, ConsultarTodosDAO<PaisEntidad>,
		ConsultarPorFiltroDAO<PaisEntidad> {
}