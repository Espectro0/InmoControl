package com.inmocontrol.negocio.fachada.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadPorIdCasoUso;
import com.inmocontrol.negocio.casouso.tipopropiedad.impl.ConsultarTipoPropiedadPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoPropiedadPorIdFachadaImpl implements ConsultarTipoPropiedadPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarTipoPropiedadPorIdCasoUso casoUso;

  public ConsultarTipoPropiedadPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarTipoPropiedadPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public TipoPropiedadEntidad ejecutar(TipoPropiedadDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo de propiedad no pueden ser nulos");
    }

    try {
      TipoPropiedadDominio dominio = new TipoPropiedadDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
