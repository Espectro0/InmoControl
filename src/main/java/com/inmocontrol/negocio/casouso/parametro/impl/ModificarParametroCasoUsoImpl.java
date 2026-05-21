package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ModificarParametroCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

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
    modificarParametro(datos);
  }

  private void validarObligatoriedadId(ParametroDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El parametro a modificar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del parametro es obligatorio.");
    }
  }

  private void validarExistenciaParametro(ParametroDominio datos) {
    ParametroEntidad existente = daoFactory.obtenerParametroDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe un parametro con el ID: " + datos.getId());
    }
  }

  private void modificarParametro(ParametroDominio datos) {
    validarUnicoPlaceholder(datos);
    ParametroEntidad entidad =
        new ParametroEntidad.Builder()
            .id(datos.getId())
            .placeholder(datos.getPlaceholder())
            .descripcion(datos.getDescripcion())
            .build();
    daoFactory.obtenerParametroDAO().actualizar(entidad.getId(), entidad);
  }

  private void validarUnicoPlaceholder(ParametroDominio datos) {
    ParametroEntidad filtro =
        new ParametroEntidad.Builder().placeholder(datos.getPlaceholder()).build();
    var resultados = daoFactory.obtenerParametroDAO().consultarPorFiltro(filtro);
    for (ParametroEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new ValidacionExcepcion(
            "Ya existe un parametro con el placeholder: " + datos.getPlaceholder());
      }
    }
  }
}
