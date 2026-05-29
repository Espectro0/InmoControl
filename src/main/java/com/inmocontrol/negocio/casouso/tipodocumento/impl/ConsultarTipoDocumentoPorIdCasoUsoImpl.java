package com.inmocontrol.negocio.casouso.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoPorIdCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.transversal.UtilObjeto;


public class ConsultarTipoDocumentoPorIdCasoUsoImpl implements ConsultarTipoDocumentoPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarTipoDocumentoPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public TipoDocumentoEntidad ejecutar(TipoDocumentoDominio datos) {
    validarObligatoriedadIdTipoDocumento(datos);
    return consultarTipoDocumento(datos);
  }

  private void validarObligatoriedadIdTipoDocumento(TipoDocumentoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El tipo de documento a consultar no es valido.",
          "Validacion fallida en ConsultarTipoDocumentoPorIdCasoUsoImpl.validarObligatoriedadIdTipoDocumento() - datos nulos");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new InmocontrolExcepcion(
          "El ID del tipo de documento es obligatorio.",
          "Validacion fallida en ConsultarTipoDocumentoPorIdCasoUsoImpl.validarObligatoriedadIdTipoDocumento() - ID nulo");
    }
  }

  private TipoDocumentoEntidad consultarTipoDocumento(TipoDocumentoDominio datos) {
    TipoDocumentoEntidad tipoDocumentoEntidad =
        daoFactory.obtenerTipoDocumentoDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(tipoDocumentoEntidad)) {
      throw new InmocontrolExcepcion(
          "No existe un tipo de documento con el ID: " + datos.getId(),
          "Validacion fallida en ConsultarTipoDocumentoPorIdCasoUsoImpl.consultarTipoDocumento() - Tipo documento no encontrado");
    }
    return tipoDocumentoEntidad;
  }
}


