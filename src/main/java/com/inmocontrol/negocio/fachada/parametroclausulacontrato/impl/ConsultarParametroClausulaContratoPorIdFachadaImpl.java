package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoPorIdCasoUso;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.ConsultarParametroClausulaContratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.ConsultarParametroClausulaContratoPorIdFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarParametroClausulaContratoPorIdFachadaImpl
    implements ConsultarParametroClausulaContratoPorIdFachada {

  private DAOFactory daoFactory;
  private ConsultarParametroClausulaContratoPorIdCasoUso casoUso;

  public ConsultarParametroClausulaContratoPorIdFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarParametroClausulaContratoPorIdCasoUsoImpl(daoFactory);
  }

  @Override
  public ParametroClausulaContratoEntidad ejecutar(ParametroClausulaContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion(
          "Los datos del parametro clausula contrato no pueden ser nulos");
    }

    try {
      ParametroClausulaContratoDominio dominio =
          new ParametroClausulaContratoDominio.Builder().id(datos.getId()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
