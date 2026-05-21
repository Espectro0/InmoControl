package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.negocio.casouso.areareferencia.RegistrarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.RegistrarAreaReferenciaCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.fachada.areareferencia.RegistrarAreaReferenciaFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarAreaReferenciaFachadaImpl implements RegistrarAreaReferenciaFachada {

  private DAOFactory daoFactory;
  private RegistrarAreaReferenciaCasoUso casoUso;

  public RegistrarAreaReferenciaFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new RegistrarAreaReferenciaCasoUsoImpl(daoFactory);
  }

  @Override
  public void ejecutar(AreaReferenciaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del area de referencia no pueden ser nulos");
    }

    try {
      daoFactory.iniciarTransaccion();
      AreaReferenciaDominio dominio =
          new AreaReferenciaDominio.Builder().nombre(datos.getNombre()).build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion(
          "Ocurrio un error registrando el area de referencia", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
