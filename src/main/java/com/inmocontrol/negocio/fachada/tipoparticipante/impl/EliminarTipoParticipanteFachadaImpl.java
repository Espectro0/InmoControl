package com.inmocontrol.negocio.fachada.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.negocio.casouso.tipoparticipante.EliminarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.casouso.tipoparticipante.impl.EliminarTipoParticipanteCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.negocio.fachada.tipoparticipante.EliminarTipoParticipanteFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarTipoParticipanteFachadaImpl implements EliminarTipoParticipanteFachada {

  private DAOFactory daoFactory;
  private EliminarTipoParticipanteCasoUso casoUso;

  public EliminarTipoParticipanteFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new EliminarTipoParticipanteCasoUsoImpl(daoFactory);
  }

  @Override
  public void ejecutar(TipoParticipanteDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo participante no pueden ser nulos");
    }

    try {
      daoFactory.iniciarTransaccion();
      var existente = daoFactory.obtenerTipoParticipanteDAO().consultarPorId(datos.getId());
      if (existente == null) {
        throw new ValidacionExcepcion(
            "El tipo de participante con id " + datos.getId() + " no existe.");
      }
      TipoParticipanteDominio dominio =
          new TipoParticipanteDominio.Builder().id(datos.getId()).build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion("Ocurrio un error eliminando el tipo participante", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
