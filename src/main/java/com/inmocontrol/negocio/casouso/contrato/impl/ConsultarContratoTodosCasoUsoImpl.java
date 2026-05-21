package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoTodosCasoUso;
import java.util.List;

public class ConsultarContratoTodosCasoUsoImpl implements ConsultarContratoTodosCasoUso {

    private DAOFactory daoFactory;

    public ConsultarContratoTodosCasoUsoImpl(DAOFactory daoFactory) {
        super();
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ContratoEntidad> ejecutar(Void datos) {
        return daoFactory.obtenerContratoDAO().consultarTodos();
    }
}