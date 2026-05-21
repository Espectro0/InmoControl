package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ModificarTipoParticipanteCasoUsoImpl;
import com.inmocontrol.negocio.casouso.tipoparticipante.ModificarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.ModificarTipoParticipanteFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ModificarTipoParticipanteFachadaImpl implements ModificarTipoParticipanteFachada {

    private DAOFactory daoFactory;
    private ModificarTipoParticipanteCasoUso casoUso;

    public ModificarTipoParticipanteFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ModificarTipoParticipanteCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(TipoParticipanteDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del tipo de participante no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder()
                    .id(datos.getId())
                    .nombre(datos.getNombre())
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error modificando el tipo de participante", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}