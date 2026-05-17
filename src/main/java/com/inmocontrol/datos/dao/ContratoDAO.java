package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ContratoEntidad;
import java.util.UUID;

public interface ContratoDAO
		extends ConsultarPorIdDAO<ContratoEntidad, UUID>, ConsultarTodosDAO<ContratoEntidad>,
				ConsultarPorFiltroDAO<ContratoEntidad>, CrearDAO<ContratoEntidad>,
				ActualizarDAO<ContratoEntidad, UUID>, EliminarDAO<UUID> {
}