package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.negocio.casouso.areareferencia.EliminarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.EliminarAreaReferenciaCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.fachada.areareferencia.EliminarAreaReferenciaFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarAreaReferenciaFachadaImpl implements EliminarAreaReferenciaFachada {

  private DAOFactory daoFactory;
  private EliminarAreaReferenciaCasoUso casoUso;

  public EliminarAreaReferenciaFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new EliminarAreaReferenciaCasoUsoImpl(daoFactory);
  }

  @Override
  public void ejecutar(AreaReferenciaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del area de referencia no pueden ser nulos");
    }

    try {
      daoFactory.iniciarTransaccion();
      var existente = daoFactory.obtenerAreaReferenciaDAO().consultarPorId(datos.getId());
      if (existente == null) {
        throw new ValidacionExcepcion(
            "El area de referencia con id " + datos.getId() + " no existe.");
      }
      AreaReferenciaDominio dominio = new AreaReferenciaDominio.Builder().id(datos.getId()).build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion(
          "Ocurrio un error eliminando el area de referencia", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
