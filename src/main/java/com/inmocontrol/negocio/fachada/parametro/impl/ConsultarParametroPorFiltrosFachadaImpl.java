package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.parametro.impl.ConsultarParametroPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.ConsultarParametroPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import java.util.List;

public class ConsultarParametroPorFiltrosFachadaImpl
    implements ConsultarParametroPorFiltrosFachada {

  private DAOFactory daoFactory;
  private ConsultarParametroPorFiltrosCasoUso casoUso;

  public ConsultarParametroPorFiltrosFachadaImpl() {
    daoFactory = DAOFactory.getFactory();
    casoUso = new ConsultarParametroPorFiltrosCasoUsoImpl(daoFactory);
  }

  @Override
  public List<ParametroEntidad> ejecutar(ParametroDTO datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("Los datos del parametro no pueden ser nulos");
    }

    try {
      ParametroDominio dominio =
          new ParametroDominio.Builder()
              .placeholder(datos.getPlaceholder())
              .descripcion(datos.getDescripcion())
              .build();
      return casoUso.ejecutar(dominio);

    } catch (Exception excepcion) {
      throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

    } finally {
      daoFactory.cerrarConexion();
    }
  }
}
