package com.inmocontrol.datos.dao;

import com.inmocontrol.datos.ActualizarDAO;
import com.inmocontrol.datos.ConsultarPorFiltroDAO;
import com.inmocontrol.datos.ConsultarPorIdDAO;
import com.inmocontrol.datos.ConsultarTodosDAO;
import com.inmocontrol.datos.CrearDAO;
import com.inmocontrol.datos.EliminarDAO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import java.util.UUID;

public interface ClausulaPorContratoDAO
    extends ConsultarPorIdDAO<ClausulaPorContratoEntidad, UUID>,
        ConsultarTodosDAO<ClausulaPorContratoEntidad>,
        ConsultarPorFiltroDAO<ClausulaPorContratoEntidad>,
        CrearDAO<ClausulaPorContratoEntidad>,
        ActualizarDAO<ClausulaPorContratoEntidad, UUID>,
        EliminarDAO<UUID> {}
