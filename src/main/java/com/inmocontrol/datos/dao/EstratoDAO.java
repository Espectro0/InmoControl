package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.EstratoEntidad;
import java.util.UUID;

public interface EstratoDAO
		extends ConsultarPorIdDAO<EstratoEntidad, UUID>, ConsultarTodosDAO<EstratoEntidad>,
				ConsultarPorFiltroDAO<EstratoEntidad> {
}