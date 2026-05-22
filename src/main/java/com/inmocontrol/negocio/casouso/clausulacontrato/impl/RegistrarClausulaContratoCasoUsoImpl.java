package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.RegistrarClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarClausulaContratoCasoUsoImpl implements RegistrarClausulaContratoCasoUso {

  private DAOFactory daoFactory;

  public RegistrarClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ClausulaContratoDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarUnicoAreaTipoTitulo(datos);
    registrarClausulaContrato(datos);
  }

  private void validarObligatoriedadCampos(ClausulaContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("La clausula contrato a registrar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getAreaReferencia())
        || UtilObjeto.esNulo(datos.getAreaReferencia().getId())) {
      throw new ValidacionExcepcion("El area de referencia es obligatoria.");
    }
    if (UtilObjeto.esNulo(datos.getTipoAplicacion())
        || UtilObjeto.esNulo(datos.getTipoAplicacion().getId())) {
      throw new ValidacionExcepcion("El tipo de aplicacion es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getTitulo()) || datos.getTitulo().isEmpty()) {
      throw new ValidacionExcepcion("El titulo es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getContenidoLegal()) || datos.getContenidoLegal().isEmpty()) {
      throw new ValidacionExcepcion("El contenido legal es obligatorio.");
    }
  }

  private void validarFormatos(ClausulaContratoDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getTitulo(), 1, 50)) {
      throw new ValidacionExcepcion("El titulo debe tener entre 1 y 50 caracteres.");
    }
    if (!UtilValidacion.validarLongitud(datos.getContenidoLegal(), 1, 200)) {
      throw new ValidacionExcepcion("El contenido legal debe tener entre 1 y 200 caracteres.");
    }
  }

  private void validarUnicoAreaTipoTitulo(ClausulaContratoDominio datos) {
    ClausulaContratoEntidad filtro =
        new ClausulaContratoEntidad.Builder()
            .areaReferencia(
                new AreaReferenciaEntidad.Builder().id(datos.getAreaReferencia().getId()).build())
            .tipoAplicacion(
                new TipoAplicacionEntidad.Builder().id(datos.getTipoAplicacion().getId()).build())
            .titulo(datos.getTitulo())
            .build();
    var resultados = daoFactory.obtenerClausulaContratoDAO().consultarPorFiltro(filtro);
    if (!resultados.isEmpty()) {
      throw new ValidacionExcepcion(
          "Ya existe una clausula con el area, tipo de aplicacion y titulo especificados");
    }
  }

  private void registrarClausulaContrato(ClausulaContratoDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerClausulaContratoDAO().consultarPorId(uuid) != null);
    ClausulaContratoEntidad entidad =
        new ClausulaContratoEntidad.Builder()
            .id(idUnico)
            .areaReferencia(
                new AreaReferenciaEntidad.Builder().id(datos.getAreaReferencia().getId()).build())
            .tipoAplicacion(
                new TipoAplicacionEntidad.Builder().id(datos.getTipoAplicacion().getId()).build())
            .titulo(UtilSanitizacion.sanitizar(datos.getTitulo()))
            .contenidoLegal(UtilSanitizacion.sanitizar(datos.getContenidoLegal()))
            .build();
    daoFactory.obtenerClausulaContratoDAO().crear(entidad);
  }
}
