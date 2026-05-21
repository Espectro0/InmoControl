package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadPorIdCasoUso;
import com.inmocontrol.negocio.casouso.propiedad.impl.ConsultarPropiedadPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.ConsultarPropiedadPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarPropiedadPorIdFachadaImpl implements ConsultarPropiedadPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarPropiedadPorIdCasoUso casoUso;

  public ConsultarPropiedadPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarPropiedadPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public PropiedadEntidad ejecutar(PropiedadDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos de la propiedad no pueden ser nulos");
    }

    try {
      PropiedadDominio dominio = new PropiedadDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
