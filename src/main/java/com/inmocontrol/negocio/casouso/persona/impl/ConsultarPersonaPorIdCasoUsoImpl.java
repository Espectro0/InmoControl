package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarPersonaPorIdCasoUsoImpl implements ConsultarPersonaPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPersonaPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public PersonaEntidad ejecutar(PersonaDominio datos) {
    validarObligatoriedadIdPersona(datos);
    return consultarPersona(datos);
  }

  private void validarObligatoriedadIdPersona(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La persona a consultar no es valida.",
          "Validacion fallida en ConsultarPersonaPorIdCasoUsoImpl.validarObligatoriedadIdPersona() - datos nulos");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la persona es obligatorio.",
          "Validacion fallida en ConsultarPersonaPorIdCasoUsoImpl.validarObligatoriedadIdPersona() - ID nulo");
    }
  }

  private PersonaEntidad consultarPersona(PersonaDominio datos) {
    PersonaEntidad personaEntidad = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(personaEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe una persona con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarPersonaPorIdCasoUsoImpl.consultarPersona() - Persona no encontrada");
    }
    return personaEntidad;
  }
}


