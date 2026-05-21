package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import java.util.UUID;

public interface ParametroClausulaContratoDAO
    extends ConsultarPorIdDAO<ParametroClausulaContratoEntidad, UUID>,
        ConsultarTodosDAO<ParametroClausulaContratoEntidad>,
        ConsultarPorFiltroDAO<ParametroClausulaContratoEntidad>,
        CrearDAO<ParametroClausulaContratoEntidad>,
        ActualizarDAO<ParametroClausulaContratoEntidad, UUID>,
        EliminarDAO<UUID> {}
