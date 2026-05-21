package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.RegistrarAreaReferenciaCasoUso;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarAreaReferenciaCasoUsoImpl implements RegistrarAreaReferenciaCasoUso {

  private DAOFactory daoFactory;

  public RegistrarAreaReferenciaCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(AreaReferenciaDominio datos) {
    validarObligatoriedadCampos(datos);
    registrarAreaReferencia(datos);
  }

  private void validarObligatoriedadCampos(AreaReferenciaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El area de referencia a registrar no es valida.");
    }
    if (UtilObjeto.esNulo(datos.getNombre()) || datos.getNombre().isEmpty()) {
      throw new ValidacionExcepcion("El nombre del area de referencia es obligatorio.");
    }
  }

  private void registrarAreaReferencia(AreaReferenciaDominio datos) {
    AreaReferenciaEntidad entidad =
        new AreaReferenciaEntidad.Builder().nombre(datos.getNombre()).build();
    daoFactory.obtenerAreaReferenciaDAO().crear(entidad);
  }
}
