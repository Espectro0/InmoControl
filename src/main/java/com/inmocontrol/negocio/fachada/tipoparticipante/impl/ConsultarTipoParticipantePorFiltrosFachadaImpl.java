package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.ConsultarTipoParticipantePorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipantePorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.ConsultarTipoParticipantePorFiltrosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarTipoParticipantePorFiltrosFachadaImpl implements ConsultarTipoParticipantePorFiltrosFachada {

    private DAOFactory daoFactory;
    private ConsultarTipoParticipantePorFiltrosCasoUso casoUso;

    public ConsultarTipoParticipantePorFiltrosFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ConsultarTipoParticipantePorFiltrosCasoUsoImpl(daoFactory);
    }

    @Override
    public List<TipoParticipanteEntidad> ejecutar(TipoParticipanteDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del tipo de participante no pueden ser nulos");
        }

        try {
            TipoParticipanteDominio dominio = new TipoParticipanteDominio.Builder()
                    .nombre(datos.getNombre())
                    .build();
            return casoUso.ejecutar(dominio);

        } catch (Exception excepcion) {
            throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}