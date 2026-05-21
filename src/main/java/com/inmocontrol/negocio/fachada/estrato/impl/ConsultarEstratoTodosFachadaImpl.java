package com.inmocontrol.negocio.fachada.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoTodosCasoUso;
import com.inmocontrol.negocio.casouso.estrato.impl.ConsultarEstratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarEstratoTodosFachadaImpl implements ConsultarEstratoTodosFachada {

  private DAOFactory daoFactory;
  private ConsultarEstratoTodosCasoUso casoUso;

  public ConsultarEstratoTodosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarEstratoTodosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<EstratoEntidad> ejecutar() {
    try {
      return casoUso.ejecutar();

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
