package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.persona.RegistrarPersonaCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilDate;
import com.inmocontrol.transversal.UtilEmail;
import com.inmocontrol.transversal.UtilIdentificador;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilTelefono;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;


public class RegistrarPersonaCasoUsoImpl implements RegistrarPersonaCasoUso {

  private DAOFactory daoFactory;

  public RegistrarPersonaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PersonaDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarUnicoNumeroIdentificacion(datos);
    registrarPersona(datos);
  }

  private void validarObligatoriedadCampos(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La persona a registrar no es valida.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarObligatoriedadCampos() - La persona a registrar no es valida."
      );
    }
    if (UtilObjeto.esNulo(datos.getTipoDocumento().getId())) {
      throw new InmocontrolExcepcion(
          "El tipo de documento es obligatorio.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarObligatoriedadCampos() - El tipo de documento es obligatorio."
      );
    }
    if (UtilObjeto.esNulo(datos.getNumeroIdentificacion())
        || datos.getNumeroIdentificacion().isEmpty()) {
      throw new InmocontrolExcepcion(
          "El numero de identificacion es obligatorio.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarObligatoriedadCampos() - El numero de identificacion es obligatorio."
      );
    }
    if (UtilObjeto.esNulo(datos.getPrimerNombre()) || datos.getPrimerNombre().isEmpty()) {
      throw new InmocontrolExcepcion(
          "El primer nombre es obligatorio.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarObligatoriedadCampos() - El primer nombre es obligatorio."
      );
    }
    if (UtilObjeto.esNulo(datos.getPrimerApellido()) || datos.getPrimerApellido().isEmpty()) {
      throw new InmocontrolExcepcion(
          "El primer apellido es obligatorio.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarObligatoriedadCampos() - El primer apellido es obligatorio."
      );
    }
  }

  private void validarFormatos(PersonaDominio datos) {
    if (!UtilIdentificador.esIdentificadorValido(datos.getNumeroIdentificacion())) {
      throw new InmocontrolExcepcion(
          "El numero de identificacion tiene un formato invalido.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarFormatos() - El numero de identificacion tiene un formato invalido."
      );
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
      throw new InmocontrolExcepcion(
          "El " + nombreCampo + " debe tener entre " + min + " y " + max + " caracteres.",
          "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarLongitudOpcional() - " + nombreCampo
      );
    }
  }

  private void validarTelefonoOpcional(String telefono) {
    if (telefono != null && !telefono.isEmpty()) {
      if (!UtilTelefono.esTelefonoValido(telefono)) {
        throw new InmocontrolExcepcion(
            "El numero telefonico tiene un formato invalido.",
            "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarTelefonoOpcional() - El numero telefonico tiene un formato invalido."
        );
      }
      if (!UtilValidacion.validarLongitud(telefono, 1, 15)) {
        throw new InmocontrolExcepcion(
            "El numero telefonico debe tener maximo 15 caracteres.",
            "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarTelefonoOpcional() - El numero telefonico debe tener maximo 15 caracteres."
        );
      }
    }
  }

  private void validarEmailOpcional(String email) {
    if (email != null && !email.isEmpty()) {
      if (!UtilEmail.esEmailValido(email)) {
        throw new InmocontrolExcepcion(
            "El correo electronico tiene un formato invalido.",
            "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarEmailOpcional() - El correo electronico tiene un formato invalido."
        );
      }
      if (!UtilValidacion.validarLongitud(email, 1, 30)) {
        throw new InmocontrolExcepcion(
            "El correo electronico debe tener maximo 30 caracteres.",
            "Validacion fallida en RegistrarPersonaCasoUsoImpl.validarEmailOpcional() - El correo electronico debe tener maximo 30 caracteres."
        );
      }
    }
  }

  private void validarUnicoNumeroIdentificacion(PersonaDominio datos) {
    PersonaEntidad existente =
        new PersonaEntidad.Builder().numeroIdentificacion(datos.getNumeroIdentificacion()).build();
    var resultados = daoFactory.obtenerPersonaDAO().consultarPorFiltro(existente);
    if (!resultados.isEmpty()) {
      throw new InmocontrolExcepcion(
          "Ya existe una persona con el numero de identificacion: "
              + datos.getNumeroIdentificacion(),
          "Validacion fallida en RegistrarPersonaCasoUsoImpl - DUI ya existe: " + datos.getNumeroIdentificacion()
      );
    }
  }

  private void registrarPersona(PersonaDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerPersonaDAO().consultarPorId(uuid) != null);
    PersonaEntidad entidad =
        new PersonaEntidad.Builder()
            .id(idUnico)
            .tipoDocumento(
                new TipoDocumentoEntidad.Builder().id(datos.getTipoDocumento().getId()).build())
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
            .edad(UtilDate.calcularEdad(datos.getFechaNacimiento()))
            .build();
    daoFactory.obtenerPersonaDAO().crear(entidad);
  }
}


