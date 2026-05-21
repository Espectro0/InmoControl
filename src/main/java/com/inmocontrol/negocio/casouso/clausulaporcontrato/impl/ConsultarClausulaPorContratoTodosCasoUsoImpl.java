package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoTodosCasoUso;
import java.util.List;

public class ConsultarClausulaPorContratoTodosCasoUsoImpl implements ConsultarClausulaPorContratoTodosCasoUso {

    private DAOFactory daoFactory;

    public ConsultarClausulaPorContratoTodosCasoUsoImpl(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ClausulaPorContratoEntidad> ejecutar(Void datos) {
        return daoFactory.obtenerClausulaPorContratoDAO().consultarTodos();
    }
}