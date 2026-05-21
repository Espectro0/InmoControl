package com.inmocontrol.negocio.fachada.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.tipopropiedad.impl.ConsultarTipoPropiedadPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarTipoPropiedadPorFiltrosFachadaImpl
    implements ConsultarTipoPropiedadPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarTipoPropiedadPorFiltrosCasoUso casoUso;

  public ConsultarTipoPropiedadPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarTipoPropiedadPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<TipoPropiedadEntidad> ejecutar(TipoPropiedadDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del tipo de propiedad no pueden ser nulos");
    }

    try {
      TipoPropiedadDominio dominio =
          new TipoPropiedadDominio.Builder().nombre(datos.getNombre()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
