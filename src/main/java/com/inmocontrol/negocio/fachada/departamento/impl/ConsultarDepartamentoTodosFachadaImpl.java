package com.inmocontrol.negocio.fachada.departamento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.departamento.ConsultarDepartamentoTodosCasoUso;
import com.inmocontrol.negocio.casouso.departamento.impl.ConsultarDepartamentoTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarDepartamentoTodosFachadaImpl implements ConsultarDepartamentoTodosFachada {

  private DAOFactory daoFactory;
  private ConsultarDepartamentoTodosCasoUso casoUso;

  public ConsultarDepartamentoTodosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarDepartamentoTodosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<DepartamentoEntidad> ejecutar() {
    try {
      return casoUso.ejecutar();

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
