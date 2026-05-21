package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.persona.ModificarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarPersonaCasoUsoImpl implements ModificarPersonaCasoUso {

  private DAOFactory daoFactory;

  public ModificarPersonaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PersonaDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaPersona(datos);
    modificarPersona(datos);
  }

  private void validarObligatoriedadId(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La persona a modificar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la persona es obligatorio.");
    }
  }

  private void validarExistenciaPersona(PersonaDominio datos) {
    PersonaEntidad existente = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe una persona con el ID: " + datos.getId());
    }
  }

  private void modificarPersona(PersonaDominio datos) {
    PersonaEntidad entidad =
        new PersonaEntidad.Builder()
            .id(datos.getId())
            .tipoDocumento(
                datos.getTipoDocumento() != null
                    ? new TipoDocumentoEntidad.Builder()
                        .id(datos.getTipoDocumento().getId())
                        .build()
                    : null)
            .numeroIdentificacion(datos.getNumeroIdentificacion())
            .primerNombre(datos.getPrimerNombre())
            .segundoNombre(datos.getSegundoNombre())
            .primerApellido(datos.getPrimerApellido())
            .segundoApellido(datos.getSegundoApellido())
            .numeroTelefonico(datos.getNumeroTelefonico())
            .correoElectronico(datos.getCorreoElectronico())
            .direccionResidencia(datos.getDireccionResidencia())
            .ciudadResidencia(
                datos.getCiudadResidencia() != null
                    ? new CiudadEntidad.Builder().id(datos.getCiudadResidencia().getId()).build()
                    : null)
            .fechaNacimiento(datos.getFechaNacimiento())
            .edad(datos.getEdad())
            .build();
    daoFactory.obtenerPersonaDAO().actualizar(entidad.getId(), entidad);
  }
}
