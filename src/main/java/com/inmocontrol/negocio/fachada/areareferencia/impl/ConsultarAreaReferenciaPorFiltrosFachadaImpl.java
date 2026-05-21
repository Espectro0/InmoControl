package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.ConsultarAreaReferenciaPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.fachada.areareferencia.ConsultarAreaReferenciaPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarAreaReferenciaPorFiltrosFachadaImpl
    implements ConsultarAreaReferenciaPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarAreaReferenciaPorFiltrosCasoUso casoUso;

  public ConsultarAreaReferenciaPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarAreaReferenciaPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<AreaReferenciaEntidad> ejecutar(AreaReferenciaDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del area de referencia no pueden ser nulos");
    }

    try {
      AreaReferenciaDominio dominio =
          new AreaReferenciaDominio.Builder().nombre(datos.getNombre()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
