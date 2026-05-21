package com.inmocontrol.negocio.casouso.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.ModificarPropiedadCasoUso;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarPropiedadCasoUsoImpl implements ModificarPropiedadCasoUso {

  private DAOFactory daoFactory;

  public ModificarPropiedadCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(PropiedadDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaPropiedad(datos);
    modificarPropiedad(datos);
  }

  private void validarObligatoriedadId(PropiedadDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La propiedad a modificar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la propiedad es obligatorio.");
    }
  }

  private void validarExistenciaPropiedad(PropiedadDominio datos) {
    PropiedadEntidad existente = daoFactory.obtenerPropiedadDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe una propiedad con el ID: " + datos.getId());
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
            .nombreInmueble(datos.getNombreInmueble())
            .descripcionInmueble(datos.getDescripcionInmueble())
            .areaMetros(datos.getAreaMetros())
            .direccion(datos.getDireccion())
            .ciudad(
                datos.getCiudad() != null
                    ? new CiudadEntidad.Builder().id(datos.getCiudad().getId()).build()
                    : null)
            .build();
    daoFactory.obtenerPropiedadDAO().actualizar(entidad.getId(), entidad);
  }
}
