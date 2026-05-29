package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ModificarParametroCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilValidacion;


public class ModificarParametroCasoUsoImpl implements ModificarParametroCasoUso {

  private DAOFactory daoFactory;

  public ModificarParametroCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParametroDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaParametro(datos);
    validarFormatos(datos);
    validarUnicoPlaceholder(datos);
    modificarParametro(datos);
  }

  private void validarObligatoriedadId(ParametroDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El parametro a modificar no es valido.",
          "Validacion fallida en ModificarParametroCasoUsoImpl.validarObligatoriedadId() - El parametro a modificar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del parametro es obligatorio.",
          "Validacion fallida en ModificarParametroCasoUsoImpl.validarObligatoriedadId() - El ID del parametro es obligatorio."
      );
    }
  }

  private void validarExistenciaParametro(ParametroDominio datos) {
    ParametroEntidad existente = daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new InmocontrolExcepcion(
          "No existe un parametro con el ID: " + datos.getId(),
          "Error en ModificarParametroCasoUsoImpl.validarExistenciaParametro() - No existe un parametro con el ID: " + datos.getId()
      );
    }
  }

  private void validarFormatos(ParametroDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getPlaceholder(), 1, 30)) {
      throw new InmocontrolExcepcion(
          "El placeholder debe tener entre 1 y 30 caracteres.",
          "Validacion fallida en ModificarParametroCasoUsoImpl.validarFormatos() - El placeholder debe tener entre 1 y 30 caracteres."
      );
    }
    if (!UtilValidacion.validarLongitud(datos.getDescripcion(), 1, 100)) {
      throw new InmocontrolExcepcion(
          "La descripcion debe tener entre 1 y 100 caracteres.",
          "Validacion fallida en ModificarParametroCasoUsoImpl.validarFormatos() - La descripcion debe tener entre 1 y 100 caracteres."
      );
    }
  }

  private void validarUnicoPlaceholder(ParametroDominio datos) {
    ParametroEntidad filtro =
        new ParametroEntidad.Builder().placeholder(datos.getPlaceholder()).build();
    var resultados = daoFactory.obtenerParametroDAO().consultarPorFiltro(filtro);
    for (ParametroEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new InmocontrolExcepcion(
            "Ya existe un parametro con el placeholder: " + datos.getPlaceholder(),
            "Validacion fallida en ModificarParametroCasoUsoImpl.validarUnicoPlaceholder() - Ya existe un parametro con el placeholder: " + datos.getPlaceholder()
        );
      }
    }
  }

  private void modificarParametro(ParametroDominio datos) {
    ParametroEntidad entidad =
        new ParametroEntidad.Builder()
            .id(datos.getId())
            .placeholder(UtilSanitizacion.sanitizar(datos.getPlaceholder()))
            .descripcion(UtilSanitizacion.sanitizar(datos.getDescripcion()))
            .build();
    daoFactory.obtenerParametroDAO().actualizar(entidad.getId(), entidad);
  }
}


