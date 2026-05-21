package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaTodosCasoUso;
import java.util.List;

public class ConsultarPersonaTodosCasoUsoImpl implements ConsultarPersonaTodosCasoUso {

    private DAOFactory daoFactory;

    public ConsultarPersonaTodosCasoUsoImpl(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    public List<PersonaEntidad> ejecutar(Void datos) {
        return daoFactory.obtenerPersonaDAO().consultarTodos();
    }
}