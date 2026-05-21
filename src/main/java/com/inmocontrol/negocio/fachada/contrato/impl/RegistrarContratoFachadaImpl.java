package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.negocio.casouso.contrato.RegistrarContratoCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.RegistrarContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.contrato.RegistrarContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarContratoFachadaImpl implements RegistrarContratoFachada {

  private DAOFactory daoFactory;
  private RegistrarContratoCasoUso casoUso;

  public RegistrarContratoFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new RegistrarContratoCasoUsoImpl(daoFactory);
  }

  @Override
  public void ejecutar(ContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del contrato no pueden ser nulos");
    }

    try {
      daoFactory.iniciarTransaccion();
      ContratoDominio dominio =
          new ContratoDominio.Builder()
              .codigoContrato(datos.getCodigoContrato())
              .fechaInicio(datos.getFechaInicio())
              .fechaFin(datos.getFechaFin())
              .propiedad(
                  datos.getPropiedad() != null
                      ? new PropiedadDominio.Builder().id(datos.getPropiedad().getId()).build()
                      : null)
              .build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion("Ocurrio un error registrando el contrato", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
