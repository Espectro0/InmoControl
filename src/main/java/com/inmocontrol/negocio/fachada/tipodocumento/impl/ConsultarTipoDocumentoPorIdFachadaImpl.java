package com.inmocontrol.negocio.fachada.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.tipodocumento.impl.ConsultarTipoDocumentoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoDocumentoPorIdFachadaImpl implements ConsultarTipoDocumentoPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarTipoDocumentoPorIdCasoUso casoUso;

  public ConsultarTipoDocumentoPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarTipoDocumentoPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public TipoDocumentoEntidad ejecutar(TipoDocumentoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo de documento no pueden ser nulos");
    }

    try {
      TipoDocumentoDominio dominio = new TipoDocumentoDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
