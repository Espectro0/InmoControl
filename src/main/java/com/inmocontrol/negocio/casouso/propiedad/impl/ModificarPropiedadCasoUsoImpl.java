package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ModificarPropiedadCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;


public class ModificarPropiedadCasoUsoImpl implements ModificarPropiedadCasoUso {

  private DAOFactory daoFactory;

  public ModificarPropiedadCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PropiedadDominio datos) {
    if (datos.getTipoPropiedad().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El tipo de propiedad es obligatorio");
    }
    if (datos.getTipoPropiedad().getNombre() != null && datos.getTipoPropiedad().getNombre().equals("N/A")) {
      throw new ValidadorExcepcion("El tipo de propiedad es obligatorio");
    }
    if (datos.getEstrato().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El estrato es obligatorio");
    }
    if (datos.getEstrato().getNombre() != null && datos.getEstrato().getNombre().equals("N/A")) {
      throw new ValidadorExcepcion("El estrato es obligatorio");
    }
    if (datos.getCiudad().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("La ciudad es obligatoria");
    }
    if (datos.getCiudad().getNombre() != null && datos.getCiudad().getNombre().equals("N/A")) {
      throw new ValidadorExcepcion("La ciudad es obligatoria");
    }
    validarObligatoriedadId(datos);
    validarExistenciaPropiedad(datos);
    validarFormatos(datos);
    validarUnicoNombreDireccion(datos);
    modificarPropiedad(datos);
  }

  private void validarObligatoriedadId(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "La propiedad a modificar no es valida.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarObligatoriedadId() - La propiedad a modificar no es valida."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID de la propiedad es obligatorio a modificar.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarObligatoriedadId() - El ID de la propiedad es obligatorio."
      );
    }
  }

  private void validarExistenciaPropiedad(PropiedadDominio datos) {
    PropiedadEntidad existente = daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe una propiedad con el ID: " + datos.getId(),
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarExistenciaPropiedad() - Propiedad no encontrada con ID: " + datos.getId()
      );
    }
  }

  private void validarFormatos(PropiedadDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getNombreInmueble(), 1, 10)) {
      throw new InmocontrolExcepcion(
          "El nombre del inmueble debe tener entre 1 y 10 caracteres.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarFormatos() - El nombre del inmueble debe tener entre 1 y 10 caracteres."
      );
    }
    if (datos.getDescripcionInmueble() != null
        && !datos.getDescripcionInmueble().isEmpty()
        && !UtilValidacion.validarLongitud(datos.getDescripcionInmueble(), 1, 100)) {
      throw new InmocontrolExcepcion(
          "La descripcion del inmueble debe tener maximo 100 caracteres.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl - descripcion muy larga"
      );
    }
    if (datos.getAreaMetros() != null
        && !UtilValidacion.validarRangoEntero(datos.getAreaMetros(), 1, 999)) {
      throw new InmocontrolExcepcion(
          "El area en metros debe estar entre 1 y 999.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarFormatos() - El area en metros debe estar entre 1 y 999."
      );
    }
    if (!UtilValidacion.validarLongitud(datos.getDireccion(), 1, 50)) {
      throw new InmocontrolExcepcion(
          "La direccion debe tener entre 1 y 50 caracteres.",
          "Validacion fallida en ModificarPropiedadCasoUsoImpl.validarFormatos() - La direccion debe tener entre 1 y 50 caracteres."
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
    for (PropiedadEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new InmocontrolExcepcion(
            "Ya existe una propiedad con el nombre: "
                + datos.getNombreInmueble()
                + " y direccion: "
                + datos.getDireccion(),
            "Validacion fallida en ModificarPropiedadCasoUsoImpl - propiedad duplicada"
        );
      }
    }
  }

  private void modificarPropiedad(PropiedadDominio datos) {
    PropiedadEntidad entidad =
        new PropiedadEntidad.Builder()
            .id(datos.getId())
            .tipoPropiedad(
                datos.getTipoPropiedad() != null
                    ? new TipoPropiedadEntidad.Builder()
                        .id(datos.getTipoPropiedad().getId())
                        .build()
                    : null)
            .estrato(
                datos.getEstrato() != null
                    ? new EstratoEntidad.Builder().id(datos.getEstrato().getId()).build()
                    : null)
            .nombreInmueble(UtilSanitizacion.sanitizar(datos.getNombreInmueble()))
            .descripcionInmueble(UtilSanitizacion.sanitizar(datos.getDescripcionInmueble()))
            .areaMetros(datos.getAreaMetros())
            .direccion(UtilSanitizacion.sanitizar(datos.getDireccion()))
            .ciudad(
                datos.getCiudad() != null
                    ? new CiudadEntidad.Builder().id(datos.getCiudad().getId()).build()
                    : null)
            .build();
    daoFactory.obtenerPropiedadDAO().actualizar(entidad.getId(), entidad);
  }
}


