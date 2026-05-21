package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ConsultarTipoParticipantePorIdCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoParticipantePorIdCasoUsoImpl
    implements ConsultarTipoParticipantePorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoParticipantePorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public TipoParticipanteEntidad ejecutar(TipoParticipanteDominio datos) {
    validarObligatoriedadIdTipoParticipante(datos);
    return consultarTipoParticipante(datos);
  }

  private void validarObligatoriedadIdTipoParticipante(TipoParticipanteDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El tipo de participante a consultar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del tipo de participante es obligatorio.");
    }
  }

  private TipoParticipanteEntidad consultarTipoParticipante(TipoParticipanteDominio datos) {
    TipoParticipanteEntidad entidad =
        daoFactory.obtenerTipoParticipanteDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(entidad)) {
      throw new ValidacionExcepcion(
          "No existe un tipo de participante con el ID: " + datos.getId());
    }
    return entidad;
  }
}
