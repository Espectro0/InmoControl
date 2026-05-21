package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadTodosCasoUso;
import java.util.List;

public class ConsultarPropiedadTodosCasoUsoImpl implements ConsultarPropiedadTodosCasoUso {

    private DAOFactory daoFactory;

    public ConsultarPropiedadTodosCasoUsoImpl(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    public List<PropiedadEntidad> ejecutar(Void datos) {
        return daoFactory.obtenerPropiedadDAO().consultarTodos();
    }
}