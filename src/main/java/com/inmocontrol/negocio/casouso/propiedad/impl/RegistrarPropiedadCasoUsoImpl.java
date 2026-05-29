package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.RegistrarPropiedadCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;


public class RegistrarPropiedadCasoUsoImpl implements RegistrarPropiedadCasoUso {

  private DAOFactory daoFactory;

  public RegistrarPropiedadCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PropiedadDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarUnicoNombreDireccion(datos);
    registrarPropiedad(datos);
  }

  private void validarObligatoriedadCampos(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La propiedad a registrar no es valida.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarObligatoriedadCampos() - La propiedad a registrar no es valida."
      );
    }
    if (UtilObjeto.esNulo(datos.getTipoPropiedad().getId())) {
      throw new InmocontrolExcepcion(
          "El tipo de propiedad es obligatorio.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarObligatoriedadCampos() - El tipo de propiedad es obligatorio."
      );
    }
    if (UtilObjeto.esNulo(datos.getNombreInmueble()) || datos.getNombreInmueble().isEmpty()) {
      throw new InmocontrolExcepcion(
          "El nombre del inmueble es obligatorio.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarObligatoriedadCampos() - El nombre del inmueble es obligatorio."
      );
    }
    if (UtilObjeto.esNulo(datos.getDireccion()) || datos.getDireccion().isEmpty()) {
      throw new InmocontrolExcepcion(
          "La direccion es obligatoria.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarObligatoriedadCampos() - La direccion es obligatoria."
      );
    }
    if (UtilObjeto.esNulo(datos.getCiudad().getId())) {
      throw new InmocontrolExcepcion(
          "La ciudad es obligatoria.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarObligatoriedadCampos() - La ciudad es obligatoria."
      );
    }
  }

  private void validarFormatos(PropiedadDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getNombreInmueble(), 1, 10)) {
      throw new InmocontrolExcepcion(
          "El nombre del inmueble debe tener entre 1 y 10 caracteres.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarFormatos() - El nombre del inmueble debe tener entre 1 y 10 caracteres."
      );
    }
    if (datos.getDescripcionInmueble() != null
        && !datos.getDescripcionInmueble().isEmpty()
        && !UtilValidacion.validarLongitud(datos.getDescripcionInmueble(), 1, 100)) {
      throw new InmocontrolExcepcion(
          "La descripcion del inmueble debe tener maximo 100 caracteres.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl - descripcion muy larga"
      );
    }
    if (datos.getAreaMetros() != null
        && !UtilValidacion.validarRangoEntero(datos.getAreaMetros(), 1, 999)) {
      throw new InmocontrolExcepcion(
          "El area en metros debe estar entre 1 y 999.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarFormatos() - El area en metros debe estar entre 1 y 999."
      );
    }
    if (!UtilValidacion.validarLongitud(datos.getDireccion(), 1, 50)) {
      throw new InmocontrolExcepcion(
          "La direccion debe tener entre 1 y 50 caracteres.",
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl.validarFormatos() - La direccion debe tener entre 1 y 50 caracteres."
      );
    }
  }

  private void validarUnicoNombreDireccion(PropiedadDominio datos) {
    PropiedadEntidad filtro =
        new PropiedadEntidad.Builder()
            .nombreInmueble(datos.getNombreInmueble())
            .direccion(datos.getDireccion())
            .build();
    var resultados = daoFactory.obtenerPropiedadDAO().consultarPorFiltro(filtro);
    if (!resultados.isEmpty()) {
      throw new InmocontrolExcepcion(
          "Ya existe una propiedad con el nombre: "
              + datos.getNombreInmueble()
              + " y direccion: "
              + datos.getDireccion(),
          "Validacion fallida en RegistrarPropiedadCasoUsoImpl - propiedad duplicada"
      );
    }
  }

  private void registrarPropiedad(PropiedadDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerPropiedadDAO().consultarPorId(uuid) != null);
    PropiedadEntidad entidad =
        new PropiedadEntidad.Builder()
            .id(idUnico)
            .tipoPropiedad(
                new TipoPropiedadEntidad.Builder().id(datos.getTipoPropiedad().getId()).build())
            .estrato(
                datos.getEstrato() != null
                    ? new EstratoEntidad.Builder().id(datos.getEstrato().getId()).build()
                    : null)
            .nombreInmueble(UtilSanitizacion.sanitizar(datos.getNombreInmueble()))
            .descripcionInmueble(UtilSanitizacion.sanitizar(datos.getDescripcionInmueble()))
            .areaMetros(datos.getAreaMetros())
            .direccion(UtilSanitizacion.sanitizar(datos.getDireccion()))
            .ciudad(new CiudadEntidad.Builder().id(datos.getCiudad().getId()).build())
            .build();
    daoFactory.obtenerPropiedadDAO().crear(entidad);
  }
}


