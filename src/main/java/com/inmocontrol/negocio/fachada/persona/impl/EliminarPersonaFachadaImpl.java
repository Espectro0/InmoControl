package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.negocio.casouso.persona.impl.EliminarPersonaCasoUsoImpl;
import com.inmocontrol.negocio.casouso.persona.EliminarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.fachada.persona.EliminarPersonaFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class EliminarPersonaFachadaImpl implements EliminarPersonaFachada {

    private DAOFactory daoFactory;
    private EliminarPersonaCasoUso casoUso;

    public EliminarPersonaFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new EliminarPersonaCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(PersonaDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la persona no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            PersonaDominio dominio = new PersonaDominio.Builder()
                    .id(datos.getId())
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error eliminando la persona", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}