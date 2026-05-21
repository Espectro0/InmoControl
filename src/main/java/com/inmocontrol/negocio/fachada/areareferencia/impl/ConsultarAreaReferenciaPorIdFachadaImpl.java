package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaPorIdCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.ConsultarAreaReferenciaPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.fachada.areareferencia.ConsultarAreaReferenciaPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarAreaReferenciaPorIdFachadaImpl
    implements ConsultarAreaReferenciaPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarAreaReferenciaPorIdCasoUso casoUso;

  public ConsultarAreaReferenciaPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarAreaReferenciaPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public AreaReferenciaEntidad ejecutar(AreaReferenciaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del area de referencia no pueden ser nulos");
    }

    try {
      AreaReferenciaDominio dominio = new AreaReferenciaDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
