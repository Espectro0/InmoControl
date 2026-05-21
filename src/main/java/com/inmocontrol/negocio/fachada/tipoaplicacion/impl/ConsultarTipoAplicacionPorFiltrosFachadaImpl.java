package com.inmocontrol.negocio.fachada.tipoaplicacion.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.tipoaplicacion.ConsultarTipoAplicacionPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.tipoaplicacion.impl.ConsultarTipoAplicacionPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarTipoAplicacionPorFiltrosFachadaImpl
    implements ConsultarTipoAplicacionPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarTipoAplicacionPorFiltrosCasoUso casoUso;

  public ConsultarTipoAplicacionPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarTipoAplicacionPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<TipoAplicacionEntidad> ejecutar(TipoAplicacionDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo de aplicacion no pueden ser nulos");
    }

    try {
      TipoAplicacionDominio dominio =
          new TipoAplicacionDominio.Builder().nombre(datos.getNombre()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
