package com.inmocontrol.negocio.fachada.participantecontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.assembler.entidad.impl.ContratoEntidadAssembler;
import com.inmocontrol.negocio.assembler.entidad.impl.PersonaEntidadAssembler;
import com.inmocontrol.negocio.assembler.entidad.impl.TipoParticipanteEntidadAssembler;
import com.inmocontrol.negocio.casouso.participantecontrato.ConsultarParticipanteContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.participantecontrato.impl.ConsultarParticipanteContratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParticipanteContratoDominio;
import com.inmocontrol.negocio.fachada.participantecontrato.ConsultarParticipanteContratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarParticipanteContratoPorFiltrosFachadaImpl
    implements ConsultarParticipanteContratoPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarParticipanteContratoPorFiltrosCasoUso casoUso;

  public ConsultarParticipanteContratoPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarParticipanteContratoPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<ParticipanteContratoEntidad> ejecutar(ParticipanteContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del participante contrato no pueden ser nulos");
    }

    try {
      ParticipanteContratoDominio dominio =
          new ParticipanteContratoDominio.Builder()
              .persona(
                  datos.getPersonaId() != null
                      ? PersonaEntidadAssembler.getInstance()
                          .ensamblarDominio(
                              new PersonaEntidad.Builder().id(datos.getPersonaId()).build())
                      : null)
              .tipoParticipante(
                  datos.getTipoParticipanteId() != null
                      ? TipoParticipanteEntidadAssembler.getInstance()
                          .ensamblarDominio(
                              new TipoParticipanteEntidad.Builder()
                                  .id(datos.getTipoParticipanteId())
                                  .build())
                      : null)
              .contrato(
                  datos.getContratoId() != null
                      ? ContratoEntidadAssembler.getInstance()
                          .ensamblarDominio(
                              new ContratoEntidad.Builder().id(datos.getContratoId()).build())
                      : null)
              .build();

      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion(
          "Ocurrio un error consultando participantes contrato por filtro", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
