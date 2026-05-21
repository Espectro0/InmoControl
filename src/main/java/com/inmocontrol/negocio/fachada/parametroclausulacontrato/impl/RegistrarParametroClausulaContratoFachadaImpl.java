package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.RegistrarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.RegistrarParametroClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.RegistrarParametroClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarParametroClausulaContratoFachadaImpl
    implements RegistrarParametroClausulaContratoFachada {

  private DAOFactory daoFactory;
  private RegistrarParametroClausulaContratoCasoUso casoUso;

  public RegistrarParametroClausulaContratoFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new RegistrarParametroClausulaContratoCasoUsoImpl(daoFactory);
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
          new ParametroClausulaContratoDominio.Builder()
              .parametro(
                  datos.getParametro() != null
                      ? new com.inmocontrol.negocio.dominio.ParametroDominio.Builder()
                          .id(datos.getParametro().getId())
                          .build()
                      : null)
              .clausulaPorContrato(
                  datos.getClausulaPorContrato() != null
                      ? new com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio.Builder()
                          .id(datos.getClausulaPorContrato().getId())
                          .build()
                      : null)
              .valor(datos.getValor())
              .build();
      casoUso.ejecutar(dominio);
      daoFactory.confirmarTransaccion();

    } catch (Exception excepcion) {
      daoFactory.cancelarTransaccion();
      throw new InmocontrolExcepcion(
          "Ocurrio un error registrando el parametro clausula contrato", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
