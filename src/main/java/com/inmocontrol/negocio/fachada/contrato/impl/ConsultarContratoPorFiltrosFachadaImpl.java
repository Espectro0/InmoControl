package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.contrato.ConsultarContratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.ConsultarContratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.contrato.ConsultarContratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarContratoPorFiltrosFachadaImpl implements ConsultarContratoPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarContratoPorFiltrosCasoUso casoUso;

  public ConsultarContratoPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarContratoPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<ContratoEntidad> ejecutar(ContratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del contrato no pueden ser nulos");
    }

    try {
      ContratoDominio dominio =
          new ContratoDominio.Builder()
              .codigoContrato(datos.getCodigoContrato())
              .esActivo(datos.getEsActivo())
              .build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
