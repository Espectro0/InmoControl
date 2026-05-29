package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.EliminarPersonaCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class EliminarPersonaCasoUsoImpl implements EliminarPersonaCasoUso {

  private DAOFactory daoFactory;

  public EliminarPersonaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PersonaDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaPersona(datos);
    eliminarPersona(datos);
  }

  private void validarObligatoriedadId(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La persona a eliminar no es valida.",
          "Validacion fallida en EliminarPersonaCasoUsoImpl.validarObligatoriedadId() - La persona a eliminar no es valida."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la persona es obligatorio.",
          "Validacion fallida en EliminarPersonaCasoUsoImpl.validarObligatoriedadId() - El ID de la persona es obligatorio."
      );
    }
  }

  private void validarExistenciaPersona(PersonaDominio datos) {
    PersonaEntidad existente = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe una persona con el ID: " + datos.getId(),
          "Validacion fallida en EliminarPersonaCasoUsoImpl.validarExistenciaPersona() - Persona no encontrada con ID: " + datos.getId()
      );
    }
  }

  private void eliminarPersona(PersonaDominio datos) {
    daoFactory.obtenerPersonaDAO().eliminar(datos.getId());
  }
}


