package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.persona.RegistrarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarPersonaCasoUsoImpl implements RegistrarPersonaCasoUso {

  private DAOFactory daoFactory;

  public RegistrarPersonaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PersonaDominio datos) {
    validarObligatoriedadCampos(datos);
    validarUnicoNumeroIdentificacion(datos);
    registrarPersona(datos);
  }

  private void validarObligatoriedadCampos(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La persona a registrar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getTipoDocumento())
        || UtilObjeto.esNulo(datos.getTipoDocumento().getId())) {
      throw new ValidacionExcepcion("El tipo de documento es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getNumeroIdentificacion())
        || datos.getNumeroIdentificacion().isEmpty()) {
      throw new ValidacionExcepcion("El numero de identificacion es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getPrimerNombre()) || datos.getPrimerNombre().isEmpty()) {
      throw new ValidacionExcepcion("El primer nombre es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getPrimerApellido()) || datos.getPrimerApellido().isEmpty()) {
      throw new ValidacionExcepcion("El primer apellido es obligatorio.");
    }
  }

  private void validarUnicoNumeroIdentificacion(PersonaDominio datos) {
    PersonaEntidad existente =
        new PersonaEntidad.Builder().numeroIdentificacion(datos.getNumeroIdentificacion()).build();
    var resultados = daoFactory.obtenerPersonaDAO().consultarPorFiltro(existente);
    if (!resultados.isEmpty()) {
      throw new ValidacionExcepcion(
          "Ya existe una persona con el numero de identificacion: "
              + datos.getNumeroIdentificacion());
    }
  }

  private void registrarPersona(PersonaDominio datos) {
    PersonaEntidad entidad =
        new PersonaEntidad.Builder()
            .tipoDocumento(
                new TipoDocumentoEntidad.Builder().id(datos.getTipoDocumento().getId()).build())
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
    daoFactory.obtenerPersonaDAO().crear(entidad);
  }
}
