package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import java.util.UUID;

public interface ClausulaContratoDAO
		extends ConsultarPorIdDAO<ClausulaContratoEntidad, UUID>, ConsultarTodosDAO<ClausulaContratoEntidad>,
				ConsultarPorFiltroDAO<ClausulaContratoEntidad>, CrearDAO<ClausulaContratoEntidad>,
				ActualizarDAO<ClausulaContratoEntidad, UUID>, EliminarDAO<UUID> {
}