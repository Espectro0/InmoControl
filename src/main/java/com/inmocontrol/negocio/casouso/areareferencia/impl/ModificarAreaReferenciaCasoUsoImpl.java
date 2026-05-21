package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ModificarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarAreaReferenciaCasoUsoImpl implements ModificarAreaReferenciaCasoUso {

  private DAOFactory daoFactory;

  public ModificarAreaReferenciaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(AreaReferenciaDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaAreaReferencia(datos);
    validarUnicoNombre(datos);
    modificarAreaReferencia(datos);
  }

  private void validarObligatoriedadId(AreaReferenciaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El area de referencia a modificar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del area de referencia es obligatorio.");
    }
  }

  private void validarExistenciaAreaReferencia(AreaReferenciaDominio datos) {
    AreaReferenciaEntidad existente =
        daoFactory.obtenerAreaReferenciaDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe un area de referencia con el ID: " + datos.getId());
    }
  }

  private void validarUnicoNombre(AreaReferenciaDominio datos) {
    AreaReferenciaEntidad filtro =
        new AreaReferenciaEntidad.Builder().nombre(datos.getNombre()).build();
    var resultados = daoFactory.obtenerAreaReferenciaDAO().consultarPorFiltro(filtro);
    for (AreaReferenciaEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new ValidacionExcepcion(
            "Ya existe un area de referencia con el nombre: " + datos.getNombre());
      }
    }
  }

  private void modificarAreaReferencia(AreaReferenciaDominio datos) {
    AreaReferenciaEntidad entidad =
        new AreaReferenciaEntidad.Builder().id(datos.getId()).nombre(datos.getNombre()).build();
    daoFactory.obtenerAreaReferenciaDAO().actualizar(entidad.getId(), entidad);
  }
}
