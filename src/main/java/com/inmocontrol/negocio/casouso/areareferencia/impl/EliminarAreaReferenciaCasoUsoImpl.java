package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.EliminarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarAreaReferenciaCasoUsoImpl implements EliminarAreaReferenciaCasoUso {

  private DAOFactory daoFactory;

  public EliminarAreaReferenciaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(AreaReferenciaDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaAreaReferencia(datos);
    validarNoDependencias(datos);
    eliminarAreaReferencia(datos);
  }

  private void validarObligatoriedadId(AreaReferenciaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El area de referencia a eliminar no es valida.");
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

  private void validarNoDependencias(AreaReferenciaDominio datos) {
    ClausulaContratoEntidad filtro =
        new ClausulaContratoEntidad.Builder()
            .areaReferencia(new AreaReferenciaEntidad.Builder().id(datos.getId()).build())
            .build();
    var resultados = daoFactory.obtenerClausulaContratoDAO().consultarPorFiltro(filtro);
    if (!resultados.isEmpty()) {
      throw new ValidacionExcepcion(
          "No es posible eliminar el area de referencia porque esta siendo usada por clausulas contrato.");
    }
  }

  private void eliminarAreaReferencia(AreaReferenciaDominio datos) {
    daoFactory.obtenerAreaReferenciaDAO().eliminar(datos.getId());
  }
}
