package com.inmocontrol.negocio.fachada.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ConsultarAreaReferenciaTodosCasoUso;
import com.inmocontrol.negocio.casouso.areareferencia.impl.ConsultarAreaReferenciaTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.areareferencia.ConsultarAreaReferenciaTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarAreaReferenciaTodosFachadaImpl
    implements ConsultarAreaReferenciaTodosFachada {

  private DAOFactory daoFactory;
  private ConsultarAreaReferenciaTodosCasoUso casoUso;

  public ConsultarAreaReferenciaTodosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarAreaReferenciaTodosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<AreaReferenciaEntidad> ejecutar() {
    try {
      return casoUso.ejecutar();

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
