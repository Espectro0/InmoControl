package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaPorIdCasoUso;
import com.inmocontrol.negocio.casouso.persona.impl.ConsultarPersonaPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.fachada.persona.ConsultarPersonaPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarPersonaPorIdFachadaImpl implements ConsultarPersonaPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarPersonaPorIdCasoUso casoUso;

  public ConsultarPersonaPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarPersonaPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public PersonaEntidad ejecutar(PersonaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos de la persona no pueden ser nulos");
    }

    try {
      PersonaDominio dominio = new PersonaDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
