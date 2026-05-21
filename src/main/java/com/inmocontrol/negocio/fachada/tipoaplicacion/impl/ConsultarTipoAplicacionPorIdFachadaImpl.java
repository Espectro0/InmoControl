package com.inmocontrol.negocio.fachada.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionPorIdCasoUso;
import com.inmocontrol.negocio.casouso.tipoaplicacion.impl.ConsultarTipoAplicacionPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoAplicacionPorIdFachadaImpl
    implements ConsultarTipoAplicacionPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarTipoAplicacionPorIdCasoUso casoUso;

  public ConsultarTipoAplicacionPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarTipoAplicacionPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public TipoAplicacionEntidad ejecutar(TipoAplicacionDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo de aplicacion no pueden ser nulos");
    }

    try {
      TipoAplicacionDominio dominio = new TipoAplicacionDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
