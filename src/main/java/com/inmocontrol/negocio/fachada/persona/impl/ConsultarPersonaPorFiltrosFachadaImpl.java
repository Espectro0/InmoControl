package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.persona.impl.ConsultarPersonaPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.fachada.persona.ConsultarPersonaPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarPersonaPorFiltrosFachadaImpl implements ConsultarPersonaPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarPersonaPorFiltrosCasoUso casoUso;

  public ConsultarPersonaPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarPersonaPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<PersonaEntidad> ejecutar(PersonaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos de la persona no pueden ser nulos");
    }

    try {
      PersonaDominio dominio =
          new PersonaDominio.Builder()
              .numeroIdentificacion(datos.getNumeroIdentificacion())
              .primerNombre(datos.getPrimerNombre())
              .primerApellido(datos.getPrimerApellido())
              .build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
