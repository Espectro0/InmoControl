package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.ConsultarClausulaContratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.ConsultarClausulaContratoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarClausulaContratoPorIdFachadaImpl
    implements ConsultarClausulaContratoPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarClausulaContratoPorIdCasoUso casoUso;

  public ConsultarClausulaContratoPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarClausulaContratoPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public ClausulaContratoEntidad ejecutar(ClausulaContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos de la clausula contrato no pueden ser nulos");
    }

    try {
      ClausulaContratoDominio dominio =
          new ClausulaContratoDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
