package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.persona.ModificarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilEmail;
import com.inmocontrol.transversal.UtilIdentificador;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilTelefono;
import com.inmocontrol.transversal.UtilValidacion;
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
    validarFormatos(datos);
    validarUnicoNumeroIdentificacion(datos);
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

  private void validarFormatos(PersonaDominio datos) {
    if (!UtilIdentificador.esIdentificadorValido(datos.getNumeroIdentificacion())) {
      throw new ValidacionExcepcion("El numero de identificacion tiene un formato invalido.");
    }
    validarLongitudOpcional(datos.getNumeroIdentificacion(), 1, 15, "numero de identificacion");
    validarLongitudOpcional(datos.getPrimerNombre(), 1, 20, "primer nombre");
    validarLongitudOpcional(datos.getSegundoNombre(), 1, 20, "segundo nombre");
    validarLongitudOpcional(datos.getPrimerApellido(), 1, 20, "primer apellido");
    validarLongitudOpcional(datos.getSegundoApellido(), 1, 20, "segundo apellido");
    validarTelefonoOpcional(datos.getNumeroTelefonico());
    validarEmailOpcional(datos.getCorreoElectronico());
    validarLongitudOpcional(datos.getDireccionResidencia(), 1, 50, "direccion de residencia");
  }

  private void validarLongitudOpcional(String valor, int min, int max, String nombreCampo) {
    if (valor != null && !valor.isEmpty() && !UtilValidacion.validarLongitud(valor, min, max)) {
      throw new ValidacionExcepcion(
          "El " + nombreCampo + " debe tener entre " + min + " y " + max + " caracteres.");
    }
  }

  private void validarTelefonoOpcional(String telefono) {
    if (telefono != null && !telefono.isEmpty()) {
      if (!UtilTelefono.esTelefonoValido(telefono)) {
        throw new ValidacionExcepcion("El numero telefonico tiene un formato invalido.");
      }
      if (!UtilValidacion.validarLongitud(telefono, 1, 15)) {
        throw new ValidacionExcepcion("El numero telefonico debe tener maximo 15 caracteres.");
      }
    }
  }

  private void validarEmailOpcional(String email) {
    if (email != null && !email.isEmpty()) {
      if (!UtilEmail.esEmailValido(email)) {
        throw new ValidacionExcepcion("El correo electronico tiene un formato invalido.");
      }
      if (!UtilValidacion.validarLongitud(email, 1, 30)) {
        throw new ValidacionExcepcion("El correo electronico debe tener maximo 30 caracteres.");
      }
    }
  }

  private void validarUnicoNumeroIdentificacion(PersonaDominio datos) {
    PersonaEntidad filtro =
        new PersonaEntidad.Builder().numeroIdentificacion(datos.getNumeroIdentificacion()).build();
    var resultados = daoFactory.obtenerPersonaDAO().consultarPorFiltro(filtro);
    for (PersonaEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new ValidacionExcepcion(
            "Ya existe una persona con el numero de identificacion: "
                + datos.getNumeroIdentificacion());
      }
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
            .numeroIdentificacion(UtilSanitizacion.sanitizar(datos.getNumeroIdentificacion()))
            .primerNombre(UtilSanitizacion.sanitizar(datos.getPrimerNombre()))
            .segundoNombre(UtilSanitizacion.sanitizar(datos.getSegundoNombre()))
            .primerApellido(UtilSanitizacion.sanitizar(datos.getPrimerApellido()))
            .segundoApellido(UtilSanitizacion.sanitizar(datos.getSegundoApellido()))
            .numeroTelefonico(
                UtilSanitizacion.sanitizar(
                    UtilValidacion.esTelefonoValido(datos.getNumeroTelefonico())
                        ? datos.getNumeroTelefonico()
                        : null))
            .correoElectronico(
                UtilSanitizacion.sanitizar(
                    UtilValidacion.esEmailValido(datos.getCorreoElectronico())
                        ? datos.getCorreoElectronico()
                        : null))
            .direccionResidencia(UtilSanitizacion.sanitizar(datos.getDireccionResidencia()))
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
