package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.RegistrarPropiedadCasoUso;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarPropiedadCasoUsoImpl implements RegistrarPropiedadCasoUso {

  private DAOFactory daoFactory;

  public RegistrarPropiedadCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PropiedadDominio datos) {
    validarObligatoriedadCampos(datos);
    validarUnicoNombreDireccion(datos);
    registrarPropiedad(datos);
  }

  private void validarObligatoriedadCampos(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La propiedad a registrar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getTipoPropiedad())
        || UtilObjeto.esNulo(datos.getTipoPropiedad().getId())) {
      throw new ValidacionExcepcion("El tipo de propiedad es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getNombreInmueble()) || datos.getNombreInmueble().isEmpty()) {
      throw new ValidacionExcepcion("El nombre del inmueble es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getDireccion()) || datos.getDireccion().isEmpty()) {
      throw new ValidacionExcepcion("La direccion es obligatoria.");
    }
    if (UtilObjeto.esNulo(datos.getCiudad()) || UtilObjeto.esNulo(datos.getCiudad().getId())) {
      throw new ValidacionExcepcion("La ciudad es obligatoria.");
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
      throw new ValidacionExcepcion(
          "Ya existe una propiedad con el nombre: "
              + datos.getNombreInmueble()
              + " y direccion: "
              + datos.getDireccion());
    }
  }

  private void registrarPropiedad(PropiedadDominio datos) {
    PropiedadEntidad entidad =
        new PropiedadEntidad.Builder()
            .tipoPropiedad(
                new TipoPropiedadEntidad.Builder().id(datos.getTipoPropiedad().getId()).build())
            .estrato(
                datos.getEstrato() != null
                    ? new EstratoEntidad.Builder().id(datos.getEstrato().getId()).build()
                    : null)
            .nombreInmueble(datos.getNombreInmueble())
            .descripcionInmueble(datos.getDescripcionInmueble())
            .areaMetros(datos.getAreaMetros())
            .direccion(datos.getDireccion())
            .ciudad(new CiudadEntidad.Builder().id(datos.getCiudad().getId()).build())
            .build();
    daoFactory.obtenerPropiedadDAO().crear(entidad);
  }
}
