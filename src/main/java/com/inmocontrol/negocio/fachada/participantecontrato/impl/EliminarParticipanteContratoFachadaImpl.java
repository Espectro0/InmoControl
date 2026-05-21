package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.EliminarParticipanteContratoCasoUsoImpl;
import com.inmocontrol.negocio.casouso.participantecontrato.EliminarParticipanteContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.EliminarParticipanteContratoFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class EliminarParticipanteContratoFachadaImpl implements EliminarParticipanteContratoFachada {

    private DAOFactory daoFactory;
    private EliminarParticipanteContratoCasoUso casoUso;

    public EliminarParticipanteContratoFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new EliminarParticipanteContratoCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(ParticipanteContratoDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del participante contrato no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            ParticipanteContratoDominio dominio = new ParticipanteContratoDominio.Builder()
                    .id(datos.getId())
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error eliminando el participante contrato", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}