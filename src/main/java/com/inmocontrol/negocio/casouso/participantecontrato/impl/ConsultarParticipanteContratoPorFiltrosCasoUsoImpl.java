package com.inmocontrol.negocio.casouso.participantecontrato.impl;

import com.inmocontrol.datos.dao.ParticipanteContratoDAO;
import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import java.util.List;

public class ConsultarParticipanteContratoPorFiltrosCasoUsoImpl
    implements ConsultarParticipanteContratoPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarParticipanteContratoPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public List<ParticipanteContratoEntidad> ejecutar(ParticipanteContratoDominio dominio) {
    ParticipanteContratoDAO dao = daoFactory.obtenerParticipanteContratoDAO();

    ParticipanteContratoEntidad filtro =
        new ParticipanteContratoEntidad.Builder()
            .persona(
                dominio.getPersona() != null
                    ? new PersonaEntidad.Builder().id(dominio.getPersona().getId()).build()
                    : null)
            .tipoParticipante(
                dominio.getTipoParticipante() != null
                    ? new TipoParticipanteEntidad.Builder()
                        .id(dominio.getTipoParticipante().getId())
                        .build()
                    : null)
            .contrato(
                dominio.getContrato() != null
                    ? new ContratoEntidad.Builder().id(dominio.getContrato().getId()).build()
                    : null)
            .build();

    return dao.consultarPorFiltro(filtro);
  }
}
