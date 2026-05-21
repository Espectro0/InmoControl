package com.inmocontrol.negocio.casouso.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisPorIdCasoUso;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarPaisPorIdCasoUsoImpl implements ConsultarPaisPorIdCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPaisPorIdCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public PaisEntidad ejecutar(PaisDominio datos) {
    validarObligatoriedadIdPais(datos);
    return consultarPais(datos);
  }

  private void validarObligatoriedadIdPais(PaisDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El pais a consultar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del pais es obligatorio.");
    }
  }

  private PaisEntidad consultarPais(PaisDominio datos) {
    PaisEntidad paisEntidad = daoFactory.obtenerPaisDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(paisEntidad)) {
      throw new ValidacionExcepcion("No existe un pais con el ID: " + datos.getId());
    }
    return paisEntidad;
  }
}
