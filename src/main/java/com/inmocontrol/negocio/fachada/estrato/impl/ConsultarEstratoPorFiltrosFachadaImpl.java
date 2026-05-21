package com.inmocontrol.negocio.fachada.estrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.casouso.estrato.ConsultarEstratoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.estrato.impl.ConsultarEstratoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarEstratoPorFiltrosFachadaImpl implements ConsultarEstratoPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarEstratoPorFiltrosCasoUso casoUso;

  public ConsultarEstratoPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarEstratoPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<EstratoEntidad> ejecutar(EstratoDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del estrato no pueden ser nulos");
    }

    try {
      EstratoDominio dominio = new EstratoDominio.Builder().nombre(datos.getNombre()).build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
