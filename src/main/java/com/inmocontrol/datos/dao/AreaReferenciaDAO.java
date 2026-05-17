package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import java.util.UUID;

public interface AreaReferenciaDAO
		extends ConsultarPorIdDAO<AreaReferenciaEntidad, UUID>, ConsultarTodosDAO<AreaReferenciaEntidad>,
				ConsultarPorFiltroDAO<AreaReferenciaEntidad>, CrearDAO<AreaReferenciaEntidad>,
				ActualizarDAO<AreaReferenciaEntidad, UUID>, EliminarDAO<UUID> {
}