package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ConsultarClausulaContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.ConsultarClausulaContratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.ConsultarClausulaContratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarClausulaContratoPorFiltrosFachadaImpl
    implements ConsultarClausulaContratoPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarClausulaContratoPorFiltrosCasoUso casoUso;

  public ConsultarClausulaContratoPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarClausulaContratoPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<ClausulaContratoEntidad> ejecutar(ClausulaContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos de la clausula contrato no pueden ser nulos");
    }

    try {
      ClausulaContratoDominio dominio =
          new ClausulaContratoDominio.Builder().titulo(datos.getTitulo()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
