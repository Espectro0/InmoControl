package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import java.util.UUID;

public interface TipoDocumentoDAO
		extends ConsultarPorIdDAO<TipoDocumentoEntidad, UUID>, ConsultarTodosDAO<TipoDocumentoEntidad>,
				ConsultarPorFiltroDAO<TipoDocumentoEntidad> {
}