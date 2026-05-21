package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ModificarClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarClausulaContratoCasoUsoImpl implements ModificarClausulaContratoCasoUso {

  private DAOFactory daoFactory;

  public ModificarClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ClausulaContratoDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaClausulaContrato(datos);
    validarUnicoAreaTipoTitulo(datos);
    modificarClausulaContrato(datos);
  }

  private void validarObligatoriedadId(ClausulaContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La clausula contrato a modificar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID de la clausula contrato es obligatorio.");
    }
  }

  private void validarExistenciaClausulaContrato(ClausulaContratoDominio datos) {
    ClausulaContratoEntidad existente =
        daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion("No existe una clausula contrato con el ID: " + datos.getId());
    }
  }

  private void validarUnicoAreaTipoTitulo(ClausulaContratoDominio datos) {
    ClausulaContratoEntidad filtro =
        new ClausulaContratoEntidad.Builder()
            .areaReferencia(
                datos.getAreaReferencia() != null
                    ? new AreaReferenciaEntidad.Builder()
                        .id(datos.getAreaReferencia().getId())
                        .build()
                    : null)
            .tipoAplicacion(
                datos.getTipoAplicacion() != null
                    ? new TipoAplicacionEntidad.Builder()
                        .id(datos.getTipoAplicacion().getId())
                        .build()
                    : null)
            .titulo(datos.getTitulo())
            .build();
    var resultados = daoFactory.obtenerClausulaContratoDAO().consultarPorFiltro(filtro);
    for (ClausulaContratoEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new ValidacionExcepcion(
            "Ya existe una clausula con el area, tipo de aplicacion y titulo especificados");
      }
    }
  }

  private void modificarClausulaContrato(ClausulaContratoDominio datos) {
    ClausulaContratoEntidad entidad =
        new ClausulaContratoEntidad.Builder()
            .id(datos.getId())
            .areaReferencia(
                datos.getAreaReferencia() != null
                    ? new AreaReferenciaEntidad.Builder()
                        .id(datos.getAreaReferencia().getId())
                        .build()
                    : null)
            .tipoAplicacion(
                datos.getTipoAplicacion() != null
                    ? new TipoAplicacionEntidad.Builder()
                        .id(datos.getTipoAplicacion().getId())
                        .build()
                    : null)
            .titulo(datos.getTitulo())
            .contenidoLegal(datos.getContenidoLegal())
            .build();
    daoFactory.obtenerClausulaContratoDAO().actualizar(entidad.getId(), entidad);
  }
}
