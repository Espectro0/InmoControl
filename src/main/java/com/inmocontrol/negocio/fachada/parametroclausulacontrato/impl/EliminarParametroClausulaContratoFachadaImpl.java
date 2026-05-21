package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.EliminarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.EliminarParametroClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.EliminarParametroClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarParametroClausulaContratoFachadaImpl
    implements EliminarParametroClausulaContratoFachada {

  private DAOFactory daoFactory;
  private EliminarParametroClausulaContratoCasoUso casoUso;

  public EliminarParametroClausulaContratoFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new EliminarParametroClausulaContratoCasoUsoImpl(daoFactory);
  }

  @Override
  public void ejecutar(ParametroClausulaContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion(
          "Los datos del parametro clausula contrato no pueden ser nulos");
    }

    try {
      daoFactory.iniciarTransaccion();
      ParametroClausulaContratoDominio dominio =
          new ParametroClausulaContratoDominio.Builder().id(datos.getId()).build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion(
          "Ocurrio un error eliminando el parametro clausula contrato", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
